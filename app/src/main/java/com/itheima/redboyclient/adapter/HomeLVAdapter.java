package com.itheima.redboyclient.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gary on 2017/2/7.
 */

public class HomeLVAdapter extends BaseAdapter {

    String[] texts = {"限时抢购","促销快报","新品上架","热门单品","推荐品牌"};
    int[] imgs = {R.drawable.home_classify_01,R.drawable.home_classify_02,R.drawable.home_classify_03,R.drawable.home_classify_04,R.drawable.home_classify_05};
    private ViewHolder viewHolder;

    public HomeLVAdapter() {
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.home_list_item,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.logo.setImageResource(imgs[position]);
        viewHolder.tv.setText(texts[position]);
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

    public class ViewHolder{
        private ImageView logo;
        private TextView tv;

        public ViewHolder(View convertView) {
            logo = (ImageView) convertView.findViewById(R.id.iv);
            tv = (TextView) convertView.findViewById(R.id.tv);
        }
    }
}
