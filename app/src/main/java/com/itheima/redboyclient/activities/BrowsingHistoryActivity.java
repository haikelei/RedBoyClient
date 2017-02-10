package com.itheima.redboyclient.activities;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.BrowsingHistoryAdapter;
import com.itheima.redboyclient.net.resp.BrowsingHistoryResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import butterknife.InjectView;

import static com.itheima.redboyclient.utils.ConstantsRedBaby.URL_BROWSING_HISTORY;

/**
 * Created by sjk on 2017/2/8.
 */

public class BrowsingHistoryActivity extends BaseActivity implements HttpLoader.HttpListener {
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.lsl)
    LoadStateLayout  loadStateLayout;
    private BrowsingHistoryResponse browsingHistoryResponse;
    private BrowsingHistoryAdapter adapter;
    @Override
    protected int initContentView() {
        return R.layout.browsing_history_activity;
    }

    @Override
    protected void initView() {
        loadStateLayout.setEmptyView(R.layout.state_empty);
        loadStateLayout.setErrorView(R.layout.state_error);
        loadStateLayout.setLoadingView(R.layout.state_loading);
        loadStateLayout.setState(LoadStateLayout.STATE_LOADING);


        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //显示左边的Home按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void initData() {

        HttpParams params = new HttpParams().put("pId", "1");
        App.HL.get(URL_BROWSING_HISTORY,params, BrowsingHistoryResponse.class,15,BrowsingHistoryActivity.this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        BrowsingHistoryResponse browsingHistoryResponse = (BrowsingHistoryResponse) response;
        if (browsingHistoryResponse.getProduct() != null ) {
            if (adapter == null) {
                //这边要把请求的数据传过去
                lv.setAdapter(new BrowsingHistoryAdapter());
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }

                });
            } else {
                adapter.notifyDataSetChanged();
            }
            loadStateLayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View

        } else {
            loadStateLayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }

    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        loadStateLayout.setState(LoadStateLayout.STATE_ERROR);
        MyToast.show(getApplicationContext(), "数据请求失败！");

    }
}
