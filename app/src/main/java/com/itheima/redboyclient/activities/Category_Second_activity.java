package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.Category_Second_Adapter;
import com.itheima.redboyclient.net.resp.CategoryResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.view.LoadStateLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

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
public class Category_Second_activity extends BaseActivity implements HttpLoader.HttpListener {

    private static final String TAG = "Category_Second";
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.category_recyclerview)
    RecyclerView categoryRecyclerview;
    @InjectView(R.id.loadstatelayout)
    LoadStateLayout loadstatelayout;


    private CategoryResponse categoryResponse;
    private Category_Second_Adapter adapter;
    private List<CategoryResponse.CategoryBean> categoryBeanList = new ArrayList<>();
    private int parentID = -1;

    @Override
    protected int initContentView() {
        return R.layout.category_second;
    }

    @Override
    protected void initView() {
        categoryRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        loadstatelayout.setEmptyView(R.layout.state_empty);
        loadstatelayout.setErrorView(R.layout.state_error);
        loadstatelayout.setLoadingView(R.layout.state_loading);
        loadstatelayout.setState(LoadStateLayout.STATE_LOADING);

        parentID = getIntent().getIntExtra("parentID", -1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        App.HL.get(ConstantsRedBaby.URL_CATEGORY, null, CategoryResponse.class, ConstantsRedBaby
                .REQUEST_CODE_CATEGORY, this).setTag(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleCateResponse((CategoryResponse) response);
    }

    private void handleCateResponse(CategoryResponse response) {
        categoryResponse = response;
        if (categoryResponse.getCategory() != null && categoryResponse.getCategory().size() > 0) {
            if (adapter == null) {
                if (categoryBeanList.size() == 0) {
                    List<CategoryResponse.CategoryBean> cateList = categoryResponse.getCategory();
                    for (int i = 0; i < cateList.size(); i++) {
                        if (cateList.get(i).getId() == parentID) {
                            getSupportActionBar().setTitle(cateList.get(i).getName());
                        }
                        if (cateList.get(i).getParentId() == parentID) {
                            categoryBeanList.add(cateList.get(i));
                        }
                    }
                }
                adapter = new Category_Second_Adapter(categoryBeanList);
                categoryRecyclerview.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
            adapter.setOnItemClickListener(new Category_Second_Adapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(Category_Second_activity.this,Category_Third_activity.class);
                    intent.putExtra("thrid_parentID",categoryBeanList.get(position).getId());
                    startActivity(intent);
                }
            });
            loadstatelayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
        } else {
            loadstatelayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        if (adapter == null)
            loadstatelayout.setState(LoadStateLayout.STATE_ERROR);
        Log.e(TAG, "onGetResponseerror: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }
}
