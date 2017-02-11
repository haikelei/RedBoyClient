package com.itheima.redboyclient.activities;

import android.widget.Button;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.BrandAdapter;
import com.itheima.redboyclient.net.resp.BrandResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import java.util.ArrayList;
import java.util.List;

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
public class BrandActivity extends BaseActivity implements HttpLoader.HttpListener {


    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.loadstatelayout)
    LoadStateLayout loadstatelayout;

    private BrandResponse brandResponse;
    private BrandAdapter adapter;
    public List<Object> objectList = new ArrayList<>();

    @Override
    protected int initContentView() {
        return R.layout.brand_activity;
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
        App.HL.get(ConstantsRedBaby.URL_BRAND, null, BrandResponse.class, ConstantsRedBaby
                .REQUEST_CODE_BRAND, BrandActivity.this).setTag(this);

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleTopicResponse((BrandResponse) response);
    }

    private void handleTopicResponse(BrandResponse response) {
        brandResponse = response;
        if (response.getBrand() != null && response.getBrand().size() > 0) {
            if (adapter == null) {
                List<BrandResponse.BrandBean> brandList = brandResponse.getBrand();
                for (int i = 0;i < brandList.size();i++) {
                    BrandResponse.BrandBean brandBean = brandList.get(i);
                    objectList.add(brandBean.getKey());
                    objectList.add(brandBean.getValue());
                }
               adapter = new BrandAdapter(getApplicationContext(),objectList);
               listview.setAdapter(adapter);
            }else {
               adapter.notifyDataSetChanged();brandResponse.getBrand();
            }
            loadstatelayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
        } else {
            loadstatelayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        if (adapter == null)
            loadstatelayout.setState(LoadStateLayout.STATE_ERROR);
        MyToast.show(getApplicationContext(), "数据请求失败！");
    }


    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

    @OnClick(R.id.button)
    public void onClick() {
        finish();
    }
}
