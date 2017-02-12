package com.itheima.redboyclient.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.CommentResponse;

/**
 * Created by gary on 2017/2/12.
 */

public class CommentAdapter extends BaseAdapter {

    private CommentResponse response;
    private Activity activity;
    public CommentAdapter(FragmentActivity activity, CommentResponse commentResponse) {
        this.activity = activity;
        response = commentResponse;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(activity, R.layout.item_comment,null);
        TextView time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView user = (TextView) convertView.findViewById(R.id.tv_username);
        TextView des = (TextView) convertView.findViewById(R.id.tv_des);
        time.setText(response.getComment().get(position).getTime()+"");
        user.setText(response.getComment().get(position).getUsername()+"");
        des.setText(response.getComment().get(position).getContent()+"");
        return convertView;
    }

    @Override
    public int getCount() {
        return response.getComment().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
