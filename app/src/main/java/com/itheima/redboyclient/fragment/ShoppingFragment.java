package com.itheima.redboyclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.LoginActivity;
import com.itheima.redboyclient.adapter.MyItemDecoration;
import com.itheima.redboyclient.adapter.ShoppingCartListAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends MainBaseFragment {
    private static final String TAG = "ShoppingFragment";
    @InjectView(R.id.rv)
    RecyclerView rv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userId = App.getUserId();
        if("".equals(userId)){
            getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
        }
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ShoppingCartListAdapter adapter = new ShoppingCartListAdapter();
//        adapter.setOnItemClickLitener(new ShoppingCartListAdapter.OnItemClickLitener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Log.e(TAG, "onItemClick: "+position );
//                // TODO: 2017/2/9
//                //item点击事件临时测试 打开商品详情页
//                Intent intent = new Intent(getActivity(), GoodDetailActivity.class);
//                getActivity().startActivity(intent);
//            }
//
//            @Override
//            public void onItemLongClick(View view, int position) {
//                Log.e(TAG, "onItemLongClick: "+position);
//                //item长按事件
//            }
//        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        rv.addItemDecoration(new MyItemDecoration());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
