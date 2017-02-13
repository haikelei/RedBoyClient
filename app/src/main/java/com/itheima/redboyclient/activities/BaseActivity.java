package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;

import org.senydevpkg.utils.MyToast;
import org.senydevpkg.utils.NetworkUtils;

import butterknife.ButterKnife;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　  ()
 * 　　  ( ) 　　　( )
 * 　　  ( ) 　　　( )
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　┻　　　┃
 * 　　┗━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 * <p>
 * Created by Seny on 2015/12/1.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected SharedPreferences sp;



    protected SharedPreferences.Editor edit;
    protected FragmentManager fm;

    public SharedPreferences getSp() {
        return sp;
    }

    public SharedPreferences.Editor getEdit() {
        return edit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        //界面中如果有EditText，默认隐藏输入法
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //初始化通用的SP&EDIT
        sp = App.SP;
        edit = App.EDIT;

        //Fragment相关
        fm = getSupportFragmentManager();

        setContentView(initContentView());
        ButterKnife.inject(this);//初始化ButterKnife
        initView();
        initListener();
        initData();

    }


    @Override
    protected void onResume() {
        super.onResume();
        checkNetworked();
    }

    protected boolean checkNetworked() {
        if (!NetworkUtils.checkNetwork(this)) {
            MyToast.show(getApplicationContext(), "手机无可用网络！");
            return false;
        }

        return true;
    }

    /**
     * 初始化contentView
     *
     * @return 返回contentView的layout id
     */
    protected abstract int initContentView();

    /**
     * 初始化View，执行findViewById操作
     */
    protected void initView() {

    }


    /**
     * 初始化监听器
     */
    protected void initListener() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_pre_in,R.anim.anim_pre_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }
}
