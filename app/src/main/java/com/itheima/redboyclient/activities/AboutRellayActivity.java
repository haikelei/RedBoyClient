package com.itheima.redboyclient.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.itheima.redboyclient.R;

import butterknife.InjectView;

/**
 * Created by sjk on 2017/2/10.
 */
public class AboutRellayActivity extends BaseActivity {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int initContentView() {
        return R.layout.about_activity;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //显示左边的Home按钮lujialei
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
