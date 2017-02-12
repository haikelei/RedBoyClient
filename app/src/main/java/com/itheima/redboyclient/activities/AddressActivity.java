package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
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
    @InjectView(R.id.add_button)
    Button addButton;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.category_recyclerview)
    RecyclerView categoryRecyclerview;
    @InjectView(R.id.loadstatelayout)
    LoadStateLayout loadstatelayout;

    private AddressAdapter adapter;
    private AddressResponse appAddressResponse;

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
        HttpParams params = new HttpParams().addHeader("userid",App.SP.getString("userid",null));
        App.HL.get(ConstantsRedBaby.URL_ADDRESSSLIST,params, AddressResponse.class,ConstantsRedBaby.REQUEST_CODE_ADDRESSSLIST,this).setTag(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick(R.id.add_button)
    public void onClick() {
        Intent intent = new Intent(AddressActivity.this, AddAddressActivity.class);
        startActivity(intent);
    }


    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleTopicResponse((AddressResponse) response);
    }

    private void handleTopicResponse(AddressResponse response) {
        appAddressResponse = response;
        final List<AddressResponse.AddressListBean> list = new ArrayList<>();
        if (appAddressResponse.getAddressList() != null && appAddressResponse.getAddressList().size() != 0) {
            if (adapter == null) {
                adapter = new AddressAdapter(list);
                categoryRecyclerview.setAdapter(adapter);
            }
            adapter.setOnItemClickListen(new AddressAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    adapter.notifyDataSetChanged(list,position);
                }
            });
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
}
