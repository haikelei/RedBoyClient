package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.ashokvarma.bottomnavigation.BadgeItem;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.db.dao.ShoppingDBDao;
import com.itheima.redboyclient.domain.EventBean;
import com.itheima.redboyclient.domain.Goods;
import com.itheima.redboyclient.fragment.MainBaseFragment;
import com.itheima.redboyclient.net.resp.CategoryResponse;
import com.itheima.redboyclient.net.resp.HomeResponse;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.utils.FragmentFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by fee1in on 2017/2/7.
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, HttpLoader.HttpListener {


    private static final String TAG = "MainActivity";
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
    private BadgeItem numberBadgeItem;
    private BottomNavigationItem item4;
    private int prePosition;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolBar();
        initLoadStateLayout();
        initBottomNavigation();
        //initFirstFragment();
        onTabSelected(0);
        EventBus.getDefault().register(this);
        showBadgeItem();

    }

    private void initToolBar() {
        toolbar.setTitle("");
        //setSupportActionBar(toolbar);
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
        numberBadgeItem = new BadgeItem()
                .setBorderWidth(0)
                .setBackgroundColorResource(R.color.colorPrimary)
                .setHideOnSelect(false)
                .show();

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.home, titleIds[0]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.search, titleIds[1]));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.brandhome, titleIds[2]));

        item4 = new BottomNavigationItem(R.drawable.cartnew, titleIds[3]);
        bottomNavigationBar.addItem(item4.setBadgeItem(numberBadgeItem));

        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.user, titleIds[4]));
        bottomNavigationBar.setActiveColor(R.color.colorPrimary);
        bottomNavigationBar.setInActiveColor(R.color.lightgray);
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


    //小圆点的限时
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEventMainThread(EventBean bean) {
        showBadgeItem();
    }

    private void showBadgeItem() {
        ShoppingDBDao dao = new ShoppingDBDao(this);
        ArrayList<Goods> list = (ArrayList<Goods>) dao.findAll();
        int goodNum = 0;
        for (Goods good : list) {
            goodNum += good.getProductNum();
        }
        if (goodNum > 99) {
            numberBadgeItem.setText("99+");
            numberBadgeItem.show(true);
        } else if (goodNum > 0) {
            numberBadgeItem.setText(goodNum + "");
            numberBadgeItem.show(true);
        } else {
            numberBadgeItem.hide(true);
        }
        numberBadgeItem.setText(goodNum+"").show();
//        item4.setBadgeItem(numberBadgeItem);
//        bottomNavigationBar.refreshDrawableState();
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
            case ConstantsRedBaby.REQUEST_CODE_CATEGORY:
                url = ConstantsRedBaby.URL_CATEGORY;
                bean = CategoryResponse.class;
                break;
            case ConstantsRedBaby.REQUEST_CODE_SHOPPING:
                //验证登陆
                //TODO 判断登陆
                String userId = App.getUserId();
                if ("".equals(userId)) {
                    //切回原来的fragment
                    bottomNavigationBar.selectTab(prePosition);
                    //显示需要登录
                    startActivity(new Intent(this, LoginActivity.class));
                    MyToast.show(this,"请先登录");
                    return;
                }
            case 4:
                //更多页面不需要访问网络
                FragmentTransaction transaction = fm.beginTransaction();
                MainBaseFragment fragment = FragmentFactory.getFragment(position);
                if (!fragment.isAdded()) {
                    transaction.add(R.id.fl_content, fragment, "" + position);
                }
                transaction.show(fragment).commit();
                lslMain.setState(LoadStateLayout.STATE_SUCCESS);
            default:
                return;
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
        prePosition = position;
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
            case ConstantsRedBaby.REQUEST_CODE_CATEGORY:
                list = ((CategoryResponse)response).getCategory();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
