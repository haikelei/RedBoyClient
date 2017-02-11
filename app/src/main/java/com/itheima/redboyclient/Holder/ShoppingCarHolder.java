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
public class ShoppingCarHolder extends RecyclerView.ViewHolder {
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

    //是否在编辑界面
    boolean isEditing = false;


    public ShoppingCarHolder(View itemView) {
        super(itemView);
        initView(itemView);


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

    public void BindData(ShoppingCarResponse.CartBean cart){

    }
}
