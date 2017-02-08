package com.itheima.redboyclient.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import java.util.List;

/**
 * Created by fee1in on 2017/2/8.
 */
public class SearchAdapter extends BaseAdapter {
    //热门搜索
    private List<String> hotSearch;
    //搜索历史
    private List<String> searchHistory;

    public SearchAdapter(List<String> hotSearch, List<String> searchHistory) {
        if (hotSearch == null) {
            throw new IllegalArgumentException("The argument hotSearch can not null");
        }
        if (searchHistory == null) {
            throw new IllegalArgumentException("The argument searchHistory can not null");
        }
        this.hotSearch = hotSearch;
        this.searchHistory = searchHistory;

    }

    @Override
    public int getCount() {
        //两个标题
        int size = 1 + hotSearch.size() + 1 + searchHistory.size();
        return size;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_search, null);
            holder = new MyViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_item_search);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        if (position == 0) {
            holder.mTextView.setText("热门搜索");
            holder.mTextView.setTextColor(Color.RED);
            convertView.setBackgroundResource(R.drawable.segment_bg);
        } else if (position <= hotSearch.size()) {
            holder.mTextView.setText(hotSearch.get(position - 1));
            holder.mTextView.setTextColor(Color.GRAY);
            convertView.setBackgroundDrawable(null);
        } else if (position == hotSearch.size() + 1) {
            holder.mTextView.setText("搜索历史");
            holder.mTextView.setTextColor(Color.RED);
            convertView.setBackgroundResource(R.drawable.segment_bg);
        } else {
            holder.mTextView.setText(hotSearch.get(position - 1 - hotSearch.size() - 1));
            holder.mTextView.setTextColor(Color.GRAY);
            convertView.setBackgroundDrawable(null);
        }

        return convertView;
    }


    class MyViewHolder {
        TextView mTextView;
    }


}
