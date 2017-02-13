package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.SettlemetCenterProductDetailResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gary on 2017/2/7.
 */

public class ProductDetailAdapter extends BaseAdapter {

    private ViewHolder viewHolder;
    private List<SettlemetCenterProductDetailResponse.ProductListBean> productList = new ArrayList<>();
    public ProductDetailAdapter(List<SettlemetCenterProductDetailResponse.ProductListBean> newList) {
        productList = newList;
    }

    @Override
    public int getCount() {
        System.out.println("aaaaa" + productList.size());
        return productList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.product_detail_listitem,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.color.setText("颜色:"+productList.get(position).getProduct().getProductProperty().get(position).getK());
        viewHolder.countprice.setText("小计:"+productList.get(position).getProduct().getPrice());
        viewHolder.logo.setImageResource(R.drawable.ic_launcher);
        App.HL.display(viewHolder.logo, ConstantsRedBaby.URL_SERVER + productList.get(position).getProduct().getPic());
        viewHolder.num.setText("数量:1个");
        viewHolder.price.setText("价格:" +productList.get(position).getProduct().getPrice());
        viewHolder.size.setText("尺码:" + productList.get(position).getProduct().getProductProperty().get(position).getV());
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
        private TextView color,size,price,num,countprice;

        public ViewHolder(View convertView) {
            logo = (ImageView) convertView.findViewById(R.id.iv_pic);
            color = (TextView) convertView.findViewById(R.id.color);
            size = (TextView) convertView.findViewById(R.id.size);
            price = (TextView) convertView.findViewById(R.id.price);
            num = (TextView) convertView.findViewById(R.id.num);
            countprice = (TextView) convertView.findViewById(R.id.countprice);
        }
    }
}
