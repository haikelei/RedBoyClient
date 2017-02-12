package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.PromotionAdapter;
import com.itheima.redboyclient.net.resp.TopicResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/2/8.
 */
public class PromotionActivity extends BaseActivity implements HttpLoader.HttpListener {


    @InjectView(R.id.toolBar)
    Toolbar toolBar;
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.myfavorite_productlist_layout)
    LoadStateLayout myfavoriteProductlistLayout;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.tv)
    TextView tv;

    private PromotionAdapter adapter;
    private TopicResponse topics;


    @Override
    protected int initContentView() {

        return R.layout.promotion_fragment;

    }

    @Override
    protected void initView() {
        initToolBar();
        initData();
    }

    protected void initToolBar() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv.setText("促销快报");
    }

    /**
     * 初始化数据
     */
    protected void initData() {

        myfavoriteProductlistLayout.setEmptyView(R.layout.state_empty);
        myfavoriteProductlistLayout.setErrorView(R.layout.state_error);
        myfavoriteProductlistLayout.setLoadingView(R.layout.state_loading);
        myfavoriteProductlistLayout.setState(LoadStateLayout.STATE_LOADING);


        HttpParams params = new HttpParams().put("page", "1").put("pageNum", "9");
        App.HL.get(ConstantsRedBaby.URL_TOPIC, params, TopicResponse.class, ConstantsRedBaby.REQUEST_CODE_TOPIC, this).setTag(this);
    }


    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleTopicResponse((TopicResponse) response);
    }

    /**
     * 处理Topic专题数据的展示
     *
     * @param response
     */
    public void handleTopicResponse(TopicResponse response) {
        topics = response;
        if (topics.topic != null && topics.topic.size() > 0) {
            if (adapter == null) {
                adapter = new PromotionAdapter(this, topics.topic);
                listview.setAdapter(adapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(PromotionActivity.this, GoodDetailActivity.class);
                        intent.putExtra("pId", topics.topic.get(position) + "");
                        startActivity(intent);
                    }
                });
            } else {
                adapter.notifyDataSetChanged(topics.topic);
            }
            myfavoriteProductlistLayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
        } else {
            myfavoriteProductlistLayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }
    }


    public void onGetResponseError(int requestCode, VolleyError error) {
        if (adapter == null)//如果当前没有设置过数据
            myfavoriteProductlistLayout.setState(LoadStateLayout.STATE_ERROR);//显示请求错误的View

        MyToast.show(this, "数据请求失败！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
