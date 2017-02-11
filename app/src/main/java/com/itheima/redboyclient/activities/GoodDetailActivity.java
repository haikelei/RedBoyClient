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
import android.view.Menu;
import android.view.View;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.fragment.CommentFragment;
import com.itheima.redboyclient.fragment.ContentDetailFragment;
import com.itheima.redboyclient.fragment.GoodsDetailFragment;
import com.itheima.redboyclient.net.resp.GoodResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by gary on 2017/2/8.
 */

public class GoodDetailActivity extends AppCompatActivity {

    private TabLayout tabs;
    private ViewPager viewpager;
    private Toolbar toolbar;
    private String pId;
    private String temppid = "1";

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

        toolbar.setNavigationIcon(R.drawable.arrowback);

        MinePagerAdapter minePagerAdapter = new MinePagerAdapter(getSupportFragmentManager());
        viewpager.setOffscreenPageLimit(3);
        viewpager.setAdapter(minePagerAdapter);
        tabs.setupWithViewPager(viewpager);

        //打开activity的时候通过intent传入商品id，这里获取商品的商品id
        pId = getIntent().getStringExtra("pId");

    }



//     * ViewPager的PagerAdapter

    public class MinePagerAdapter extends FragmentPagerAdapter {


        Fragment[] fragments;
        String[] titles = new String[]{"商品", "详情", "评价"};

        public MinePagerAdapter(FragmentManager fm) {
            super(fm);

            //测试时pid用1，以后改成pid
            HttpParams params = new HttpParams().put("pId", temppid);
            App.HL.get(ConstantsRedBaby.URL_GOODDETAIL, params, GoodResponse.class, ConstantsRedBaby.REQUEST_CODE_GOODDETAIL, new HttpLoader.HttpListener() {
                @Override
                public void onGetResponseSuccess(int requestCode, IResponse response) {
                    GoodResponse goodResponse = (GoodResponse) response;
                    fragments = new Fragment[]{GoodsDetailFragment.newInstance(goodResponse), ContentDetailFragment.newInstance(goodResponse), CommentFragment.newInstance()};
                }

                @Override
                public void onGetResponseError(int requestCode, VolleyError error) {

                }
            });


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
