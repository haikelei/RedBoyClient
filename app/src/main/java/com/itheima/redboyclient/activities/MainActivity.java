package com.itheima.redboyclient.activities;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.MainPagerAdapter;
import com.itheima.redboyclient.view.NoScrollViewPager;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fee1in on 2017/2/7.
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.address_manager_add_text)
    TextView addressManagerAddText;
    @InjectView(R.id.title_bar)
    FrameLayout titleBar;
    @InjectView(R.id.imgHome)
    RadioButton imgHome;
    @InjectView(R.id.imgClassify)
    RadioButton imgClassify;
    @InjectView(R.id.imgSearch)
    RadioButton imgSearch;
    @InjectView(R.id.imgShoppingCar)
    RadioButton imgShoppingCar;
    @InjectView(R.id.imgMore)
    RadioButton imgMore;
    @InjectView(R.id.linToolBar)
    RadioGroup linToolBar;
    @InjectView(R.id.vp_main)
    NoScrollViewPager vpMain;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        vpMain.setAdapter(mainPagerAdapter);
    }

    @OnClick({R.id.imgHome, R.id.imgClassify, R.id.imgSearch, R.id.imgShoppingCar, R.id.imgMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHome:
                vpMain.setCurrentItem(0,false);
                break;
            case R.id.imgClassify:
                vpMain.setCurrentItem(1,false);
                break;
            case R.id.imgSearch:
                vpMain.setCurrentItem(2,false);
                break;
            case R.id.imgShoppingCar:
                vpMain.setCurrentItem(3,false);
                break;
            case R.id.imgMore:
                vpMain.setCurrentItem(4,false);
                break;
        }
    }

}
