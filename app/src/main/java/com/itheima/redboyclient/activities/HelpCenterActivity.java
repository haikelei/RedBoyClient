package com.itheima.redboyclient.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.HelpCenterAdapter;
import com.itheima.redboyclient.net.resp.HelpResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.itheima.redboyclient.utils.ConstantsRedBaby.REQUEST_CODE_REGIST;

/**
 * Created by sjk on 2017/2/8.
 */

public class HelpCenterActivity extends BaseActivity implements  HttpLoader.HttpListener{


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.tv_kefu)
    TextView tvKefu;

    @Override
    protected int initContentView() {
        return R.layout.help_center_activity;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //textView.setText("注册");
        //显示左边的Home按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        App.HL.post(ConstantsRedBaby.URL_REGIST,null,HelpResponse.class,REQUEST_CODE_REGIST,HelpCenterActivity.this);
    }

    //客服按钮的点击事件
    @OnClick(R.id.tv_kefu)
    public void onClick() {
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        //这边要把请求的数据传过去
        lv.setAdapter(new HelpCenterAdapter());
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
