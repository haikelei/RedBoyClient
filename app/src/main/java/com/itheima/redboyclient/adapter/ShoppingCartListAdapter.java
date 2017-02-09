package com.itheima.redboyclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import java.security.KeyStore;


/**
 * Created by gary on 2017/2/8.
 */

 public class ShoppingCartListAdapter extends RecyclerView.Adapter<ShoppingCartListAdapter.MyHolder> {

    private static final String TAG = "ShoppingCartListAdapter";

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_listitem, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        //购物车条目编辑按钮的点击事件
        holder.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.isEditing = !holder.isEditing;

                if(holder.isEditing){   //编辑状态
                    holder.editState.setVisibility(View.VISIBLE);
                    holder.normalState.setVisibility(View.GONE);
                    holder.tvEdit.setText("完成");
                }else{ //非编辑状态
                    holder.editState.setVisibility(View.GONE);
                    holder.normalState.setVisibility(View.VISIBLE);
                    holder.tvEdit.setText("编辑");
                }


            }
        });

        //购物车商品详情点击事件回调
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickLitener != null){
                    mOnItemClickLitener.onItemClick(v,position);
                }
            }
        });
        //购物车商品详情长按事件回调
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mOnItemClickLitener != null){
                    mOnItemClickLitener.onItemLongClick(v,position);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        private TextView tvEdit;
        private boolean isEditing = false;
        private RelativeLayout editState;
        private RelativeLayout normalState;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_good_num);
            tvEdit = (TextView) itemView.findViewById(R.id.tv_edit);
            editState = (RelativeLayout) itemView.findViewById(R.id.rl_edit);
            normalState = (RelativeLayout) itemView.findViewById(R.id.rl_normal);

        }
    }

    //recycerview的item的事件回调
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
