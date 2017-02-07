package com.itheima.redboyclient.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.HomeResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gary on 2017/2/7.
 */

public class HomeVPAdapter extends PagerAdapter{
    private static final String TAG = "HomeVPAdapter";
    ArrayList<HomeResponse.HomeTopicBean> homeTopic;
    Activity context;

    public HomeVPAdapter(List<HomeResponse.HomeTopicBean> homeTopic, Activity activity) {
        this.homeTopic = (ArrayList<HomeResponse.HomeTopicBean>) homeTopic;
        context = activity;
        Log.e(TAG, "HomeVPAdapter: " + homeTopic.toString() );
    }

    @Override
    public int getCount() {
        return homeTopic.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        App.HL.display(imageView, ConstantsRedBaby.URL_SERVER + homeTopic.get(position).getPic());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
