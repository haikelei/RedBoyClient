package com.itheima.redboyclient.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.net.resp.NewProductDetailResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

/**
 * Created by gary on 2017/2/7.
 */

public class DetailAdapter extends PagerAdapter {
    NewProductDetailResponse.ProductBean bean;
    Activity context;

    public DetailAdapter(NewProductDetailResponse.ProductBean productBean,Activity activity) {
        bean = productBean;
        context = activity;
    }

    @Override
    public int getCount() {
        return bean.getBigPic().size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        App.HL.display(imageView, ConstantsRedBaby.URL_SERVER + bean.getBigPic().get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
