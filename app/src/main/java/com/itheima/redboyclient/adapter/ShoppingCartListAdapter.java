package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;

import com.itheima.redboyclient.Holder.ShoppingCarHolder;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import java.util.List;


/**
 * Created by gary on 2017/2/8.
 */

public class ShoppingCartListAdapter extends BaseAdapter {
    protected List<ShoppingCarResponse.CartBean> list;

    public ShoppingCartListAdapter(List<ShoppingCarResponse.CartBean> list) {
        super();
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. 初始化holder
        ShoppingCarHolder holder;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.shoppingcart_listitem,null);
            holder = new ShoppingCarHolder(convertView);
        }else{
            holder = (ShoppingCarHolder) convertView.getTag();

        }
        //3. 绑定数据
        holder.setCart(list.get(position));
        holder.bindData();
        //4. 增加炫酷动画
        //一开始缩小
        ViewHelper.setScaleX(convertView, 0.5f);
        ViewHelper.setScaleY(convertView, 0.5f);
        //执行放大动画
        ViewPropertyAnimator.animate(convertView).scaleX(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(400).start();
        ViewPropertyAnimator.animate(convertView).scaleY(1.0f).setInterpolator(new OvershootInterpolator()).setDuration(400).start();
        return convertView;
    }


}
