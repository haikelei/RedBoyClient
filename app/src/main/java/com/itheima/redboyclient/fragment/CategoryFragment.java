package com.itheima.redboyclient.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.Category_Second_activity;
import com.itheima.redboyclient.adapter.CategoryAdapter;
import com.itheima.redboyclient.net.resp.CategoryResponse;

import org.senydevpkg.net.resp.IResponse;

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
public class CategoryFragment extends MainBaseFragment{


    @InjectView(R.id.category_recyclerview)
    RecyclerView recyclerView;

    private static final String TAG = "CategoryFragment";
    private List<CategoryResponse.CategoryBean> categoryBeanList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;

    @Override
    protected int getRootViewId() {
        return R.layout.category_first;
    }


    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initData();
    }


    private void initData() {
        //App.HL.get(ConstantsRedBaby.URL_CATEGORY, null, CategoryResponse.class, ConstantsRedBaby
                //.REQUEST_CODE_CATEGORY, this).setTag(this);
        IResponse data = getData();
        CategoryResponse response = (CategoryResponse) data;
        handleCateResponse(response);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mActivity = null;
        ButterKnife.reset(this);
    }


    private void handleCateResponse(CategoryResponse response) {
        if (response.getCategory() != null && response.getCategory().size() > 0) {
            Log.i(TAG,"获取的数据有没有");
            if (categoryAdapter == null) {
                if (categoryBeanList.size() == 0) {
                    List<CategoryResponse.CategoryBean> cateList = response.getCategory();
                    for (int i = 0;i < cateList.size();i++) {
                        if (cateList.get(i).getParentId() == 0) {
                            categoryBeanList.add(cateList.get(i));
                        }
                    }
                }
                categoryAdapter = new CategoryAdapter(categoryBeanList);
                recyclerView.setAdapter(categoryAdapter);
            }else {
                categoryAdapter.notifyDataSetChanged();
            }

            categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(getContext(),Category_Second_activity.class);
                    intent.putExtra("parentID",position + 1);
                    startActivity(intent);
                }
            });
        }
    }
}
