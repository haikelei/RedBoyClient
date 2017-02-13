package com.itheima.redboyclient.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Cache;
import com.android.volley.VolleyError;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.domain.EventBean;
import com.itheima.redboyclient.fragment.CommentFragment;
import com.itheima.redboyclient.fragment.ContentDetailFragment;
import com.itheima.redboyclient.fragment.GoodsDetailFragment;
import com.itheima.redboyclient.net.resp.GoodResponse;
import com.itheima.redboyclient.net.resp.HomeResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by gary on 2017/2/8.
 */

public class GoodDetailActivity extends AppCompatActivity {

    private static final String TAG = "GoodDetailActivity";
    private TabLayout tabs;
    private ViewPager viewpager;
    private Toolbar toolbar;
    private String pId;
    private Fragment[] fragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_good_detail);
        tabs = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pId = getIntent().getStringExtra("pId");

        HttpParams params = new HttpParams().put("pId",pId);
        App.HL.get(ConstantsRedBaby.URL_GOODDETAIL,params,GoodResponse.class,ConstantsRedBaby.REQUEST_CODE_GOODDETAIL, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {

                GoodResponse goodResponse = (GoodResponse) response;
                fragments = new Fragment[]{GoodsDetailFragment.newInstance(goodResponse),
                        ContentDetailFragment.newInstance(goodResponse), CommentFragment.newInstance(pId)};
                MinePagerAdapter minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
                viewpager.setOffscreenPageLimit(3);
                viewpager.setAdapter(minePagerAdapter);
                tabs.setupWithViewPager(viewpager);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });

    }



//     * ViewPager的PagerAdapter

    public class MinePagerAdapter extends FragmentPagerAdapter {

        String[] titles = new String[]{"商品", "详情", "评价"};


        public MinePagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void setSelected(int position){
        viewpager.setCurrentItem(position);
    }

}
