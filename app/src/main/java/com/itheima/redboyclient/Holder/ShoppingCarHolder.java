package com.itheima.redboyclient.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;

/**
 * Created by fee1in on 2017/2/11.
 */
public class ShoppingCarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //正常页面商品标题
    TextView tvTitle;
    //正常页面商品详情
    TextView tvDetail;
    //正常页面商品价格
    TextView tvPrice;
    //正常页面商品个数
    TextView tvNum;
    //正常页面父控件
    LinearLayout llNormal;
    //编辑页面减少商品数量
    TextView tvReduce;
    //编辑页面商品数量
    TextView tvEditNum;
    //编辑页面增加商品数量
    TextView tvAdd;
    //编辑页面商品详情
    TextView tvEditDetail;
    //编辑页面删除商品
    TextView tvDelete;
    //编辑页面父控件
    LinearLayout llEdit;
    //库存信息
    TextView tvStock;
    //编辑按钮
    TextView tvEdit;


    public void setCart(ShoppingCarResponse.CartBean cart) {
        this.cart = cart;
    }

    private ShoppingCarResponse.CartBean cart;

    public ShoppingCarHolder(View itemView) {
        super(itemView);
        initView(itemView);
        initListener();
        itemView.setTag(this);


    }


    private void initView(View itemView) {
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        tvNum = (TextView) itemView.findViewById(R.id.tv_num);
        llNormal = (LinearLayout) itemView.findViewById(R.id.ll_normal);
        tvReduce = (TextView) itemView.findViewById(R.id.tv_reduce);
        tvEditNum = (TextView) itemView.findViewById(R.id.tv_edit_num);
        tvAdd = (TextView) itemView.findViewById(R.id.tv_add);
        tvEditDetail = (TextView) itemView.findViewById(R.id.tv_edit_detail);
        tvDelete = (TextView) itemView.findViewById(R.id.tv_delete);
        llEdit = (LinearLayout) itemView.findViewById(R.id.ll_edit);
        tvEdit = (TextView) itemView.findViewById(R.id.tv_edit);
        tvStock = (TextView) itemView.findViewById(R.id.tv_stock);
    }

    private void initListener() {
        tvEdit.setOnClickListener(this);
    }

    public void bindData() {
        switchEditPager();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                boolean editing = cart.isEditing();
                cart.setEditing(!editing);
                switchEditPager();
                break;
        }
    }

    private void switchEditPager() {
        if (cart.isEditing()) {
            llNormal.setVisibility(View.GONE);
            llEdit.setVisibility(View.VISIBLE);
            tvEdit.setText("完成");
        } else {
            llNormal.setVisibility(View.VISIBLE);
            llEdit.setVisibility(View.GONE);
            tvEdit.setText("编辑");

        }

    }
}
