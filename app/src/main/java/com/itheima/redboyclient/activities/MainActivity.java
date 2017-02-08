package com.itheima.redboyclient.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.fragment.BaseFragment;
import com.itheima.redboyclient.utils.FragmentFactory;

import butterknife.InjectView;

/**
 * Created by fee1in on 2017/2/7.
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {


    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.address_manager_add_text)
    TextView addressManagerAddText;
    @InjectView(R.id.title_bar)
    FrameLayout titleBar;
    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;

    private int[] titleIds = {R.string.menu_home, R.string.menu_search, R.string.menu_classify, R.string.menu_shopping, R.string.menu_more};

    @Override
    protected int initContentView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initBottomNavigation();
        initFirstFragment();


    }

    private void initBottomNavigation() {

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[0]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[1]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[2]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "购物车"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[4]));
        bottomNavigationBar.setActiveColor(R.color.btn_normal);
        bottomNavigationBar.setInActiveColor(R.color.inActive);
        bottomNavigationBar.setFirstSelectedPosition(0);
        bottomNavigationBar.initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void initFirstFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        for (int i = 0; i < titleIds.length; i++) {
            Fragment fragment = supportFragmentManager.findFragmentByTag(i + "");
            if (fragment != null) {
                fragmentTransaction.remove(fragment);
            }
        }
        fragmentTransaction.commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, FragmentFactory.getFragment(0), "0").commit();
    }


    @Override
    public void onTabSelected(int position) {
        FragmentTransaction transaction = fm.beginTransaction();
        BaseFragment fragment = FragmentFactory.getFragment(position);
        if (!fragment.isAdded()) {
            transaction.add(R.id.fl_content, fragment, "" + position);
        }
        transaction.show(fragment).commit();
    }

    @Override
    public void onTabUnselected(int position) {
        fm.beginTransaction().hide(FragmentFactory.getFragment(position)).commit();
    }

    @Override
    public void onTabReselected(int position) {

    }
}
