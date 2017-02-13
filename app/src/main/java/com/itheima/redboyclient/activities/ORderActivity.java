package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.MyorderAdapter;
import com.itheima.redboyclient.net.resp.MyOrderResponse;
import com.itheima.redboyclient.net.resp.OrderResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/13.
 */
public class ORderActivity extends BaseActivity implements HttpLoader.HttpListener {
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.bt1)
    Button bt1;
    @InjectView(R.id.bt2)
    Button bt2;
    @InjectView(R.id.bt3)
    Button bt3;
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.text_sale)
    TextView textSale;
    @InjectView(R.id.text_price)
    TextView textPrice;
    @InjectView(R.id.text_comment)
    TextView textComment;
    @InjectView(R.id.loadstatelayout)
    LoadStateLayout loadstatelayout;

    private MyorderAdapter adapter;
    private OrderResponse topics;
    String userId;
    boolean islogin;

    private boolean saleState = false; //是否被点击
    private boolean priceState = false; //是否被点击
    private boolean commentState = false; //是否被点击

    private String type = "1";//默认为页数

    @Override
    protected int initContentView() {
        return R.layout.myorder_acticity;
    }

    @Override
    protected void initView() {

        loadstatelayout.setEmptyView(R.layout.state_empty);
        loadstatelayout.setErrorView(R.layout.state_error);
        loadstatelayout.setLoadingView(R.layout.state_loading);
        loadstatelayout.setState(LoadStateLayout.STATE_LOADING);
    }

    @Override
    protected void initData() {


        HttpParams params = new HttpParams();
        params.addHeader("userid", userId);
        params.put("page", "0").put("pageNum", "10").put("type", type);
        App.HL.get(ConstantsRedBaby.URL_ORDER, params, MyOrderResponse.class, ConstantsRedBaby.REQUEST_CODE_ORDER, this).setTag(this);

    }


    @OnClick({R.id.button, R.id.bt1, R.id.bt2, R.id.bt3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                finish();
                break;
            case R.id.bt1:
                saleState = true;
                type = "1";
                requestAndNotifyDataChanged();
                break;
            case R.id.bt2:
                commentState = true;
                type = "2";
                requestAndNotifyDataChanged();
                break;
            case R.id.bt3:
                commentState = true;
                type = "3";
                requestAndNotifyDataChanged();
                break;
        }
    }


    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleTopicResponse((OrderResponse) response);
    }

    /**
     * 处理Topic专题数据的展示
     *
     * @param response
     */
    public void handleTopicResponse(OrderResponse response) {
        topics = response;
        if (topics.getOrderList() != null && topics.getOrderList().size() > 0) {
            if (adapter == null) {
                adapter = new MyorderAdapter(this, topics.getOrderList());
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(ORderActivity.this, MyOrderActivity.class));
                    }
                });

            } else {
                adapter.notifyDataSetChanged(topics.getOrderList());
            }
            loadstatelayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
        } else {
            loadstatelayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }
    }


    public void onGetResponseError(int requestCode, VolleyError error) {
        if (adapter == null)//如果当前没有设置过数据
            loadstatelayout.setState(LoadStateLayout.STATE_ERROR);//显示请求错误的View

        MyToast.show(this, "数据请求失败！");
    }

    public void requestAndNotifyDataChanged() {
        changeState();

        HttpParams params = new HttpParams();
        params.addHeader("userid", userId);
        params.put("page", "0").put("pageNum", "10").put("type", type);
        App.HL.get(ConstantsRedBaby.URL_ORDER, params, MyOrderResponse.class, ConstantsRedBaby.REQUEST_CODE_ORDER, this).setTag(this);
    }

    /**
     * 状态的改变
     */
    public void changeState() {
        if (saleState) {
            textSale.setTextColor(Color.WHITE);
            bt1.setBackgroundResource(R.drawable.segment_selected_1_bg);
        } else {
            textSale.setTextColor(Color.BLACK);
            bt1.setBackgroundResource(R.drawable.segment_normal_1_bg);
        }
        if (priceState) {
            textPrice.setTextColor(Color.WHITE);
            bt2.setBackgroundResource(R.drawable.segment_selected_2_bg);
        } else {
            textPrice.setTextColor(Color.BLACK);
            bt2.setBackgroundResource(R.drawable.segment_normal_2_bg);
        }
        if (commentState) {
            textComment.setTextColor(Color.WHITE);
            bt3.setBackgroundResource(R.drawable.segment_selected_2_bg);
        } else {
            textComment.setTextColor(Color.BLACK);
            bt3.setBackgroundResource(R.drawable.segment_normal_2_bg);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}