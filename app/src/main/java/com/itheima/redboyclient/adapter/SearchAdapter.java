package com.itheima.redboyclient.adapter;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.damain.SearchTitleBean;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by fee1in on 2017/2/8.
 */
public class SearchAdapter extends BaseAdapter implements View.OnClickListener {
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
    //条目点击监听
    private ItemOnClickListener listener;

    public void setItemOnClickListener(ItemOnClickListener listener) {
        this.listener = listener;
    }

    private SharedPreferences.Editor edit;
    private static final int HOTTITLE = 0;
    private static final int HOTSEARCH = 1;
    private static final int HISTORYTITLE = 2;
    private static final int SEARCHHISTORY = 3;


    public SearchAdapter(SearchTitleBean hotTitle, List<String> hotSearch, SearchTitleBean historyTitle, List<String> searchHistory) {
        this.mHotSearch = hotSearch;
        this.mSearchHistory = searchHistory;
        this.hotTitle = hotTitle;
        this.historyTitle = historyTitle;
        showHotSearch = new ArrayList<>();
        showSearchHistory = new ArrayList<>();
        edit = App.EDIT;
    }

    @Override
    public int getCount() {
        //两个标题
        int size = 1 + showHotSearch.size() + 1 + showSearchHistory.size();
        return size;
    }

    public void refresh() {
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
            //设置识别码
            holder.setItemCode(HOTTITLE);

        } else if (position <= showHotSearch.size()) {

            //热门搜索条目
            String itemName = showHotSearch.get(position - 1);
            setItemName(convertView, holder, itemName);
            //设置识别码
            holder.setItemCode(HOTSEARCH);

        } else if (position == showHotSearch.size() + 1) {

            //设置搜索历史标题
            setTitleView(convertView, holder, historyTitle.getTitle());
            //设置箭头旋转
            setRotate(holder.mImageButton, historyTitle.isShow());
            //设置识别码
            holder.setItemCode(HISTORYTITLE);
        } else {
            //搜索历史条目
            String itemName = showSearchHistory.get(position - 1 - showHotSearch.size() - 1);
            setItemName(convertView, holder, itemName);
            //设置识别码
            holder.setItemCode(SEARCHHISTORY);
        }
        convertView.setOnClickListener(this);
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


    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {
        String itemName = null;
        MyViewHolder holder = (MyViewHolder) v.getTag();
        switch (holder.getItemCode()) {
            case HOTTITLE:
                //改变热门搜索的显示状态，刷新页面
                boolean hotIsShow = hotTitle.isShow();
                hotTitle.setShow(!hotIsShow);
                refresh();
                break;
            case HISTORYTITLE:
                //改变搜索历史的显示状态，刷新页面
                boolean historyIsShow = historyTitle.isShow();
                historyTitle.setShow(!historyIsShow);
                refresh();
                break;


            case SEARCHHISTORY:
            case HOTSEARCH:
                //获得点击条目的内容
                itemName = holder.mTextView.getText().toString();

                if (ConstantsRedBaby.NO_HISTORY.equals(itemName)) {
                    //如果点击的是没有搜索历史 直接返回
                    return;
                }
                if (listener != null) {
                    listener.onClick(itemName);
                }
                break;
        }


    }


    class MyViewHolder {
        TextView mTextView;
        ImageButton mImageButton;
        int item;

        public int getItemCode() {
            return item;
        }

        public void setItemCode(int item) {
            this.item = item;
        }
    }

    public interface ItemOnClickListener {
        void onClick(String itemName);
    }

}
