package com.itheima.redboyclient.adapter;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.HotProductResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gary on 2017/2/7.
 */

public class HotProductAdapter extends BaseAdapter {

    private ViewHolder viewHolder;
    private List<HotProductResponse.ProductListBean> newList = new ArrayList<>();

    public HotProductAdapter(List<HotProductResponse.ProductListBean> list) {
        this.newList = list;
    }

    @Override
    public int getCount() {
        return newList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.hot_product_listitem, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvName.setText(newList.get(position).getName());
        viewHolder.market_price.setText(newList.get(position).getMarketPrice() + "元");
        viewHolder.vip_price.setText(newList.get(position).getPrice() + "元");
        App.HL.display(viewHolder.ivIcon, ConstantsRedBaby.URL_SERVER + newList.get(position).getPic());

        setAnim(convertView);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        private ImageView ivIcon;
        private TextView tvName, market_price, vip_price;

        public ViewHolder(View convertView) {
            ivIcon = (ImageView) convertView.findViewById(R.id.iv_pic);
            tvName = (TextView) convertView.findViewById(R.id.tv_name);
            market_price = (TextView) convertView.findViewById(R.id.market_price);
            vip_price = (TextView) convertView.findViewById(R.id.vip_price);
        }
    }

    public void setAnim(final View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value = (Float) valueAnimator.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
            }
        });
        animator.setDuration(400);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }
}
