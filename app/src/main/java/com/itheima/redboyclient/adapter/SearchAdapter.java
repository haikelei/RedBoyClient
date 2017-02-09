package com.itheima.redboyclient.adapter;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.SearchTitleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fee1in on 2017/2/8.
 */
public class SearchAdapter extends BaseAdapter {
    //热门搜索数据
    private List<String> showHotSearch;
    //搜索历史数据
    private List<String> showSearchHistory;
    //热门搜索标题
    private SearchTitleBean hotTitle;
    //搜索历史标题
    private SearchTitleBean historyTitle;
    //记录热门搜索和搜索历史
    private List<String> mHotSearch;
    private List<String> mSearchHistory;

    public SearchAdapter(SearchTitleBean hotTitle, List<String> hotSearch, SearchTitleBean historyTitle, List<String> searchHistory) {
        this.mHotSearch = hotSearch;
        this.mSearchHistory = searchHistory;
        this.hotTitle = hotTitle;
        this.historyTitle = historyTitle;
        showHotSearch = new ArrayList<>();
        showSearchHistory = new ArrayList<>();
    }

    @Override
    public int getCount() {
        //两个标题
        int size = 1 + showHotSearch.size() + 1 + showSearchHistory.size();
        return size;
    }

    public void initData() {
        //根据标题的状态确定是否显示数据
        showHotSearch.clear();
        showSearchHistory.clear();
        if (hotTitle.isShow()) {
            showHotSearch.addAll(mHotSearch);
        }
        if (historyTitle.isShow()) {
            showSearchHistory.addAll(mSearchHistory);
        }
        notifyDataSetChanged();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_search, null);
            holder = new MyViewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_item_search);
            holder.mImageButton = (ImageButton) convertView.findViewById(R.id.ib_item_down);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }

        if (position == 0) {
            //设置热门搜索标题
            setTitleView(convertView, holder, hotTitle.getTitle());
            //设置箭头旋转
            setRotate(holder.mImageButton, hotTitle.isShow());

        } else if (position <= showHotSearch.size()) {

            //热门搜索条目
            String itemName = showHotSearch.get(position - 1);
            setItemName(convertView, holder, itemName);

        } else if (position == showHotSearch.size() + 1) {

            //设置搜索历史标题
            setTitleView(convertView, holder, historyTitle.getTitle());
            //设置箭头旋转
            setRotate(holder.mImageButton, historyTitle.isShow());
        } else {
            //搜索历史条目
            String itemName = showSearchHistory.get(position - 1 - showHotSearch.size() - 1);
            setItemName(convertView, holder, itemName);
        }

        return convertView;
    }

    private void setItemName(View convertView, MyViewHolder holder, String itemName) {
        holder.mTextView.setText(itemName);
        holder.mTextView.setTextColor(Color.GRAY);
        convertView.setBackgroundDrawable(null);
        holder.mImageButton.setVisibility(View.INVISIBLE);
    }

    private void setTitleView(View convertView, MyViewHolder holder, String title) {
        holder.mTextView.setText(title);
        holder.mTextView.setTextColor(Color.RED);
        convertView.setBackgroundResource(R.drawable.segment_bg);
        holder.mImageButton.setVisibility(View.VISIBLE);
    }


    private void setRotate(View view, boolean isShow) {
        if (isShow) {
            //显示数据，则小箭头向上
            view.setRotation(180);
        } else {
            //不显示数据，则小箭头向下
            view.setRotation(0);
        }
    }

    public String getItemName(View convertView){
        MyViewHolder holder = (MyViewHolder) convertView.getTag();
        return holder.mTextView.getText().toString();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    class MyViewHolder {
        TextView mTextView;
        ImageButton mImageButton;
    }


}
