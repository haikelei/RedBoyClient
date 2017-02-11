package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.senydevpkg.base.BaseHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　()
 * 　　  ( ) 　　( )
 * 　　  ( ) 　　( )
 * 　　┏━┛┻━━━━━━┛┻┓
 * 　　┃　　　━　　 ┃
 * 　　┃　┳┛　  ┗┳ ┃
 * 　　┃　　　┻　　 ┃
 * 　　┗━━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┗━━┣┓
 * 　　　　┃　　　　┏━━━━┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 */
public abstract class BasicAdapter<T> extends BaseAdapter{

    protected final Context mContext;
    public List<T> mData = new ArrayList<>();

    public BasicAdapter(Context mContext, List<T> data) {
        this.mContext = mContext;
        this.mData = data;
    }

    public void notifyDataSetChanged(List<T> newData) {
        this.mData = newData;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
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
        BaseHolder<T> baseHolder;
        if (convertView == null) {
            baseHolder = getHolder(position);
        }else {
            baseHolder = (BaseHolder<T>) convertView.getTag();
        }
        baseHolder.bindData(mData.get(position));
        return baseHolder.getHolderView();
    }

    protected abstract BaseHolder<T> getHolder(int position);
}
