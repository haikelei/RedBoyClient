package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import butterknife.InjectView;
import butterknife.OnClick;

import static com.itheima.redboyclient.utils.ConstantsRedBaby.REQUEST_CODE_HELP;

/**
 * Created by sjk on 2017/2/8.
 */

public class HelpCenterActivity extends BaseActivity implements HttpLoader.HttpListener {


    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.tv_kefu)
    TextView tvKefu;
    @InjectView(R.id.xian)
    TextView xian;
    @InjectView(R.id.lsl)
    LoadStateLayout loadstatelayout;
    private HelpResponse helpResponse;
    private HelpCenterAdapter adapter;

    @Override
    protected int initContentView() {
        return R.layout.help_center_activity;
    }

    @Override
    protected void initView() {
        loadstatelayout.setEmptyView(R.layout.state_empty);
        loadstatelayout.setErrorView(R.layout.state_error);
        loadstatelayout.setLoadingView(R.layout.state_loading);
        loadstatelayout.setState(LoadStateLayout.STATE_LOADING);

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
        App.HL.get(ConstantsRedBaby.URL_HELP, null, HelpResponse.class, REQUEST_CODE_HELP, HelpCenterActivity.this);
    }

    //客服按钮的点击事件
    @OnClick(R.id.tv_kefu)
    public void onClick() {
        // TODO: 2017/2/10 客服按钮具体逻辑
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        helpResponse = (HelpResponse) response;
        if (helpResponse.getHelpList() != null && helpResponse.getHelpList().size() > 0) {
            if (adapter == null) {
                //这边要把请求的数据传过去
                lv.setAdapter(new HelpCenterAdapter(this, helpResponse.getHelpList()));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(HelpCenterActivity.this, HelpDetailPageActivity.class);
                        intent.putExtra("position", position);
                        startActivity(intent);
                    }

                });
            } else {
                adapter.notifyDataSetChanged();
            }
            loadstatelayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View

        } else {
            loadstatelayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }

        ViewGroup.LayoutParams layoutParams = loadstatelayout.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        loadstatelayout.setLayoutParams(layoutParams);
        xian.setVisibility(View.VISIBLE);
        tvKefu.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        xian.setVisibility(View.GONE);
        tvKefu.setVisibility(View.GONE);
        loadstatelayout.setState(LoadStateLayout.STATE_ERROR);
        MyToast.show(getApplicationContext(), "数据请求失败！");
    }
    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

}
