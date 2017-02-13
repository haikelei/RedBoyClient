package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.HelpDetailResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import butterknife.InjectView;

import static com.itheima.redboyclient.utils.ConstantsRedBaby.REQUEST_CODE_HELP_DETAIL;


/**
 * Created by sjk on 2017/2/9.
 */
public class HelpDetailPageActivity extends BaseActivity implements HttpLoader.HttpListener {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tv_content)
    TextView tvContent;
    private int position;

    @Override
    protected int initContentView() {
        return R.layout.help_detail_activity;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
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
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        HttpParams params = new HttpParams().put("id", "1");
        App.HL.get(ConstantsRedBaby.URL_HELP_DETAIL,params,HelpDetailResponse.class,REQUEST_CODE_HELP_DETAIL,HelpDetailPageActivity.this);

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        HelpDetailResponse helpDetailResponse = (HelpDetailResponse) response;
        String title = helpDetailResponse.getHelpDetailList().get(position).getTitle();
        String content = helpDetailResponse.getHelpDetailList().get(position).getContent();
        tvTitle.setText(title);
        tvContent.setText(content);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        MyToast.show(getApplicationContext(), "数据请求失败！");
    }
    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

}
