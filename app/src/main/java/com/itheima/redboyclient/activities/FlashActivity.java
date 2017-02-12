package com.itheima.redboyclient.activities;


import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.FlashAdapter;
import com.itheima.redboyclient.net.resp.FlashResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

/**
 * Created by Administrator on 2017/2/10.
 */
public class FlashActivity extends BaseActivity implements HttpLoader.HttpListener, View.OnClickListener {


    private Button button;
    private TextView tv;
    private LoadStateLayout loadstatelayout;
    private ListView listView;
    private FlashAdapter adapter;
    private FlashResponse topics;

    @Override
    protected int initContentView() {
        return R.layout.searchresult_activity;
    }

    @Override
    protected void initView() {

        button = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.tv);
        loadstatelayout = (LoadStateLayout) findViewById(R.id.loadstatelayout);
        listView = (ListView) findViewById(R.id.listview);

        loadstatelayout.setEmptyView(R.layout.state_empty);
        loadstatelayout.setErrorView(R.layout.state_error);
        loadstatelayout.setLoadingView(R.layout.state_loading);
        loadstatelayout.setState(LoadStateLayout.STATE_LOADING);
    }

    @Override
    protected void initListener() {
        button.setOnClickListener(this);
    }



    @Override
    protected void initData() {
        tv.setText("限时抢购");
        HttpParams params = new HttpParams().put("page", "1").put("pageNum", "9");
        App.HL.get(ConstantsRedBaby.URL_FLASH, params, FlashResponse.class, ConstantsRedBaby.REQUEST_CODE_FLASH, this).setTag(this);
    }

    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleTopicResponse((FlashResponse) response);
    }

    /**
     * 处理Topic专题数据的展示
     *
     * @param response
     */
    public void handleTopicResponse(FlashResponse response) {
        topics = response;
        if (topics.getProductList() != null && topics.getProductList().size() > 0) {
            if (adapter == null) {
                adapter = new FlashAdapter(this, topics.getProductList());
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(FlashActivity.this,GoodDetailActivity.class);
                        intent.putExtra("pId",topics.getProductList().get(position).getId()+"");
                        startActivity(intent);
                    }
                });

            } else {
                adapter.notifyDataSetChanged(topics.getProductList());
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

    @Override
    public void onClick(View v) {
        finish();
    }
}
