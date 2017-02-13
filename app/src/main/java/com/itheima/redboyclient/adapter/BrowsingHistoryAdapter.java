package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.BrowsingHistoryActivity;
import com.itheima.redboyclient.net.resp.BrowsingHistoryResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;

/**
 * Created by sjk on 2017/2/10.
 */

public class BrowsingHistoryAdapter extends BaseAdapter {

    private ArrayList<BrowsingHistoryResponse> list;
    private BrowsingHistoryActivity activity;
    public BrowsingHistoryAdapter(ArrayList<BrowsingHistoryResponse> list, BrowsingHistoryActivity browsingHistoryActivity) {
        this.list = list;
        activity = browsingHistoryActivity;
    }

    @Override
    public int getCount() {
        return list.size();
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
        ViewHolder viewHolder= null;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.browsing_history,null);
            viewHolder = new ViewHolder();
            viewHolder.pinlun = (TextView) convertView.findViewById(R.id.pinglun);
            viewHolder.iv_pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.market_price = (TextView) convertView.findViewById(R.id.market_price);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.pinlun.setText(list.get(position).getProduct().getCommentCount());
        App.HL.display(viewHolder.iv_pic, ConstantsRedBaby.URL_BROWSING_HISTORY +list.get(position)
                .getProduct().getPics().get(0) );
        viewHolder.tv_name .setText(list.get(position).getProduct().getName());
        viewHolder.market_price.setText(list.get(position).getProduct().getMarketPrice());
        viewHolder.tv_price.setText(list.get(position).getProduct().getPrice());
        return convertView;
    }

    public class ViewHolder {
        private ImageView iv_pic;
        private TextView tv_name;
        private  TextView market_price;
        private  TextView pinlun;
        private TextView tv_price;

    }
}
