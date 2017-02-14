package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.UserInfoResponse;
import com.itheima.redboyclient.present.Logout;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.utils.ToastUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by yudenghao on 2017/2/10.
 */

public class AccountCenterActivity extends BaseActivity implements HttpLoader.HttpListener {
    private static final String TAG = "AccountCenterActivity";
    TextView mLougout;
    TextView acUsername;   //用户名
    @InjectView(R.id.member_mum)
    TextView member;  //会员等级
    @InjectView(R.id.integral)
    TextView integral;  //总积分
    @InjectView(R.id.my_order_count)
    TextView myOrderCount;   //我的订单数量
    @InjectView(R.id.my_order_rl)
    RelativeLayout myOrder;  //我的订单点击
    @InjectView(R.id.address_manage_rl)
    RelativeLayout addressManage;  //地址管理点击
    @InjectView(R.id.my_card_count)
    TextView cardCount;  //礼品卡数量
    @InjectView(R.id.my_favorite_rl)
    RelativeLayout myFavorite;  //礼品卡点击
    @InjectView(R.id.my_favorites_count)
    TextView favoritesCounts;  //收藏夹数量
    @InjectView(R.id.recent_browse_rl)
    RelativeLayout recentBrowse;  //收藏夹点击
    private Toolbar mToolbar;
    private TextView mTextView;
    private int bonus;
    private String level;
    private String orderCount;
    private String favoritescounts;
    private String username;
    private UserInfoResponse mUserInfoResponse;
    String userId;
    String bouns;
    boolean islogin;
    String name;
    String levels;
    @Override
    protected int initContentView() {
        return R.layout.accountcenter_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar = (Toolbar) findViewById(R.id.ac_toolbar);
        mTextView = (TextView) findViewById(R.id.ac_tv_title);
        acUsername = (TextView) findViewById(R.id.ac_username);
        mLougout = (TextView) findViewById(R.id.logout);
        initToolBar();
    }




    private void initToolBar() {
        //默认ToolBar的标题在左侧，我们不需要
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mTextView.setText("账户中心");
        //显示左边的Home按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initData() {
        super.initData();
         islogin = App.SP.getBoolean("islogin", false);
        if (islogin) {
            userId = App.SP.getString("userid",null);
        }
        Log.d(TAG, "initData: ==============" + userId);
        HttpParams params = new HttpParams();
        params.addHeader("userid",userId);
        App.HL.get(ConstantsRedBaby.URL_ACCOUNT_CENTER, params, UserInfoResponse.class, ConstantsRedBaby.REQUEST_CODE_ACCOUNT_CENTER, AccountCenterActivity.this).setTag(this);

    }



    @Override
    protected void initListener() {
        super.initListener();
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*
        * 退出登录
        * */
 /*       mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                            Logout out = new Logout();
                            out.logout();
                            boolean isLogout = App.SP.getBoolean("islogout",false);
                        if (isLogout) {
                            App.EDIT.putBoolean("islogin", false);
                            App.EDIT.commit();
                            finish();
                        }
                        break;
                }
                return true;
            }
        });*/

        mLougout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout out = new Logout();
                out.logout();
                boolean isLogout = App.SP.getBoolean("islogout",false);
                if (isLogout) {
                    App.EDIT.putBoolean("islogin", false);
                    App.EDIT.commit();
                    finish();
                }
            }
        });
    }


    public void startActivity(Class clazz, boolean isFinish) {
        if (isFinish) {
            startActivity(new Intent(getApplicationContext(), clazz));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), clazz));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.my_order_rl, R.id.address_manage_rl, R.id.my_favorite_rl, R.id.recent_browse_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_order_rl:   //我的订单点击
                startActivity(MyOrderActivity.class,false);
                break;
            case R.id.address_manage_rl:  //地址管理点击
                break;
            case R.id.my_favorite_rl:   //礼品卡点击
                break;
            case R.id.recent_browse_rl: //收藏夹点击
                startActivity(new Intent(this,BookmarksActivity.class));
                break;
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (!islogin) {
            return;
        }
        handleLoginResopnse((UserInfoResponse)response);
    }

    private void handleLoginResopnse(UserInfoResponse response) {
        if (!islogin) {
            acUsername.setVisibility(View.GONE);
            member.setVisibility(View.GONE);
            integral.setVisibility(View.GONE);
            myOrderCount.setVisibility(View.GONE);
            favoritesCounts.setVisibility(View.GONE);
            cardCount.setVisibility(View.GONE);
            return;
        }
        mUserInfoResponse = response;
        if ( mUserInfoResponse!= null) {
            Log.d(TAG, "handleLoginResopnse: ----------" + mUserInfoResponse.toString());
            String name = App.SP.getString("username",null);
            if (TextUtils.isEmpty(name)) {
                return;
            }
            //String levels = mUserInfoResponse.userInfo.level;
          //  String bouns = mUserInfoResponse.userInfo.bonus + "";
            if (mUserInfoResponse.userInfo != null) {
                bouns = mUserInfoResponse.userInfo.bonus + "";
            }
            if (mUserInfoResponse.userInfo != null) {
                levels =  mUserInfoResponse.userInfo.level;
            }
            if (!TextUtils.isEmpty(name)) {
                acUsername.setText(name);
            }
            if (!TextUtils.isEmpty(levels)) {

                member.setText(levels);
            }
            if (!TextUtils.isEmpty(bouns)) {
                integral.setText(bouns);
             }
    if (mUserInfoResponse.userInfo == null) {
        return;
    }
            if (!TextUtils.isEmpty(mUserInfoResponse.userInfo.orderCount)) {
                myOrderCount.setText("(" + mUserInfoResponse.userInfo.orderCount + ")");
                App.EDIT.putString("orderCount",mUserInfoResponse.userInfo.orderCount).commit();
            }

            if (!TextUtils.isEmpty(mUserInfoResponse.userInfo.favoritesCount)) {
                favoritesCounts.setText("(" + mUserInfoResponse.userInfo.favoritesCount + ")");
                App.EDIT.putString("favoritesCount",mUserInfoResponse.userInfo.favoritesCount);
                }
            }
        }


    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(getApplicationContext(),error +"加载数据失败请重试",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }
}


