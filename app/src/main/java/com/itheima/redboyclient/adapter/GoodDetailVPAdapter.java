package com.itheima.redboyclient.adapter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.GoodResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.resp.IResponse;

/**
 * Created by gary on 2017/2/10.
 */

public class GoodDetailVPAdapter extends PagerAdapter {
    private GoodResponse response;
    private Activity activity;

    public GoodDetailVPAdapter(IResponse response, FragmentActivity activity) {
        this.response = (GoodResponse) response;
        this.activity = activity;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //详情页大图加载
        ImageView imageView = new ImageView(activity);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (response.getProduct().getBigPic().size() == 0) {
            imageView.setImageResource(R.drawable.icon);
        } else {
            App.HL.display(imageView, ConstantsRedBaby.URL_SERVER + response.getProduct().getBigPic().get(position));
        }
        container.addView(imageView);
        return imageView;

    }

    @Override
    public int getCount() {
        return response.getProduct().getBigPic().size() == 0 ? 1:response.getProduct().getBigPic().size() ;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
