package com.itheima.redboyclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.redboyclient.R;


/**
 * Created by gary on 2017/2/8.
 */

 public class ShoppingCartListAdapter extends RecyclerView.Adapter<ShoppingCartListAdapter.MyHolder> {

    private static final String TAG = "ShoppingCartListAdapter";

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: " );
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shoppingcart_listitem, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
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
        return 20;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;
        public MyHolder(View itemView) {
            super(itemView);
            Log.e(TAG, "MyHolder: ");
            tv = (TextView) itemView.findViewById(R.id.tv_good_num);
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
