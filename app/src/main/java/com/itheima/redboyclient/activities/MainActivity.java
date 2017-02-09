package com.itheima.redboyclient.activities;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.fragment.MainBaseFragment;
import com.itheima.redboyclient.net.resp.HomeResponse;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.utils.FragmentFactory;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import java.util.List;

import butterknife.InjectView;

/**
 * Created by fee1in on 2017/2/7.
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, HttpLoader.HttpListener {


    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.lsl_main)
    LoadStateLayout lslMain;

    private int[] titleIds = {R.string.menu_home, R.string.menu_search, R.string.menu_classify, R.string.menu_shopping, R.string.menu_more};

    @Override
    protected int initContentView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar();
        initLoadStateLayout();
        initBottomNavigation();
        //initFirstFragment();
        onTabSelected(0);

    }

    private void initToolBar() {
        toolbar.setTitle("");
        tvTitle.setText("首页");
    }

    private void initLoadStateLayout() {
        lslMain.setEmptyView(R.layout.state_empty);
        lslMain.setErrorView(R.layout.state_error);
        lslMain.setLoadingView(R.layout.state_loading);
        lslMain.setState(LoadStateLayout.STATE_LOADING);
    }

    private void initBottomNavigation() {

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[0]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[1]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[2]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[3]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, titleIds[4]));
        bottomNavigationBar.setActiveColor(R.color.btn_normal);
        bottomNavigationBar.setInActiveColor(R.color.inActive);
        bottomNavigationBar.setFirstSelectedPosition(0);
        bottomNavigationBar.initialise();
        bottomNavigationBar.setTabSelectedListener(this);
    }

    private void initFirstFragment() {
//        FragmentTransaction fragmentTransaction = fm.beginTransaction();
//        for (int i = 0; i < titleIds.length; i++) {
//            Fragment fragment = fm.findFragmentByTag(i + "");
//            if (fragment != null) {
//                fragmentTransaction.remove(fragment);
//            }
//        }
//        fragmentTransaction.commit();
//        getSupportFragmentManager().beginTransaction().add(R.id.fl_content, FragmentFactory.getFragment(0), "0").commit();

    }


    @Override
    public void onTabSelected(int position) {
//        FragmentTransaction transaction = fm.beginTransaction();
//        BaseFragment fragment = FragmentFactory.getFragment(position);
//        if (!fragment.isAdded()) {
//            transaction.add(R.id.fl_content, fragment, "" + position);
//        }
//        transaction.show(fragment).commit();
        //改变标题栏
        tvTitle.setText(titleIds[position]);
        String url = null;
        Class bean = null;
        int requestCode = position;
        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_HOME:
                url = ConstantsRedBaby.URL_HOME;
                bean = HomeResponse.class;
                break;
            case ConstantsRedBaby.REQUEST_CODE_RECOMMEND:
                url = ConstantsRedBaby.URL_SEARCH_RECOMMEND;
                bean = SearchRecommendResponse.class;
                break;
            case 2:
                //TODO
                break;
            case 3:
                //Todo
                break;
            case 4:
                //Todo
                break;
        }
        MainBaseFragment fragment = FragmentFactory.getFragment(position);
        FragmentTransaction transaction = fm.beginTransaction();
        //如果fragment未被添加过，先访问网络，获取到数据再显示fragment
        //如果已经fragment已经被添加过了，直接显示fragment，再访问网络获取最新数据
        if (fragment.isAdded()) {
            lslMain.setState(LoadStateLayout.STATE_SUCCESS);
            transaction.show(fragment).commit();
        }else{
            lslMain.setState(LoadStateLayout.STATE_LOADING);
        }
        App.HL.get(url, null, bean, requestCode, this).setTag(this);

    }

    @Override
    public void onTabUnselected(int position) {
        //fragment从选中状态到未选中状态，隐藏fragment
        fm.beginTransaction().hide(FragmentFactory.getFragment(position)).commit();
    }

    @Override
    public void onTabReselected(int position) {

    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        MainBaseFragment fragment = FragmentFactory.getFragment(requestCode);
        List list = null;
        switch (requestCode) {
            case ConstantsRedBaby.REQUEST_CODE_HOME:
                list = ((HomeResponse)response).getHomeTopic();
                break;
            case ConstantsRedBaby.REQUEST_CODE_RECOMMEND:
                list = ((SearchRecommendResponse) response).getSearchKeywords();
                break;
        }

        if (list != null && list.size() > 0) {
            //获取数据成功
            lslMain.setState(LoadStateLayout.STATE_SUCCESS);
            fragment.setData(response);
            //将fragment添加进事务，并显示出来
            FragmentTransaction transaction = fm.beginTransaction();
            if (!fragment.isAdded()) {
                transaction.add(R.id.fl_content, fragment, "" + requestCode);
            }
            transaction.show(fragment).commit();

        } else {
            lslMain.setState(LoadStateLayout.STATE_EMPTY);
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        MainBaseFragment fragment = FragmentFactory.getFragment(requestCode);
        if (!fragment.isAdded()) {
            //fragment未被添加显示错误页面
            lslMain.setState(LoadStateLayout.STATE_ERROR);
        }
        MyToast.show(getApplicationContext(), "数据请求失败！");
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }
}
