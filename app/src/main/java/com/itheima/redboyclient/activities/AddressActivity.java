package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.AddressAdapter;
import com.itheima.redboyclient.net.resp.AddressResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　()
 * 　　  ( ) 　　( )
 * 　　  ( ) 　　( )
 * 　　┏━┛┻━━━━━━┛┻┓
 * 　　┃　　　━　　 ┃
 * 　　┃　┳┛　  ┗┳ ┃
 * 　　┃　　　┻　　 ┃
 * 　　┗━━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┗━━┣┓
 * 　　　　┃　　　　┏━━━━┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 */
public class AddressActivity extends BaseActivity implements HttpLoader.HttpListener {


    private static final String TAG = "AddressActivity";

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.category_recyclerview)
    RecyclerView categoryRecyclerview;
    @InjectView(R.id.loadstatelayout)
    LoadStateLayout loadstatelayout;
    @InjectView(R.id.address_button)
    Button addressButton;

    private AddressAdapter adapter;
    private AddressResponse appAddressResponse;
    private List<AddressResponse.AddressListBean> list;

    @Override
    protected int initContentView() {
        return R.layout.address_manage_activity;
    }

    @Override
    protected void initView() {
        categoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("地址管理");

        loadstatelayout.setEmptyView(R.layout.state_empty);
        loadstatelayout.setErrorView(R.layout.state_error);
        loadstatelayout.setLoadingView(R.layout.state_loading);
        loadstatelayout.setState(LoadStateLayout.STATE_LOADING);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        HttpParams params = new HttpParams().addHeader("userid", App.SP.getString("userid", ""));

        App.HL.get(ConstantsRedBaby.URL_ADDRESSSLIST, params, AddressResponse.class,
                ConstantsRedBaby.REQUEST_CODE_ADDRESSSLIST, this, false).setTag(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        list = new ArrayList<>();
        adapter = new AddressAdapter(this, list);
        categoryRecyclerview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "99999999999999999999");
        HttpParams params = new HttpParams().addHeader("userid", App.SP.getString("userid", ""));
        App.HL.get(ConstantsRedBaby.URL_ADDRESSSLIST, params, AddressResponse.class,
                ConstantsRedBaby.REQUEST_CODE_ADDRESSSLIST, this, false).setTag(this);
    }


    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        Log.i(TAG, "走了这!!!!!!!!!!!!!!!!!!!!!!");
        handleTopicResponse((AddressResponse) response);
    }

    private void handleTopicResponse(AddressResponse response) {
        appAddressResponse = response;

        if (appAddressResponse.getAddressList() != null && appAddressResponse.getAddressList()
                .size() != 0) {
            list.clear();
            list.addAll(appAddressResponse.getAddressList());
            adapter.notifyDataSetChanged();


//            adapter.setOnItemClickListen(new AddressAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(View v, int position) {
//                    adapter.notifyDataSetChanged(list,position);
//                }
//            });
            loadstatelayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
        } else {
            loadstatelayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }

    }


    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        MyToast.show(getApplicationContext(), "数据获取失败111！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }

    public void refresh() {
        HttpParams params = new HttpParams().addHeader("userid", App.SP.getString("userid", ""));
        App.HL.get(ConstantsRedBaby.URL_ADDRESSSLIST, params, AddressResponse.class,
                ConstantsRedBaby.REQUEST_CODE_ADDRESSSLIST, this, false).setTag(this);
    }

    @OnClick(R.id.address_button)
    public void onClick() {
        Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
        startActivity(intent);
    }
}
