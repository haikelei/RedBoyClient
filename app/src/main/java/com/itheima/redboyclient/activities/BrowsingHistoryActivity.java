package com.itheima.redboyclient.activities;


import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.BrowsingHistoryAdapter;
import com.itheima.redboyclient.db.dao.RecordDBDao;
import com.itheima.redboyclient.net.resp.BrowsingHistoryResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

import static com.itheima.redboyclient.utils.ConstantsRedBaby.REQUEST_CODE_BROWSING_HISTORY;
import static com.itheima.redboyclient.utils.ConstantsRedBaby.URL_BROWSING_HISTORY;

/**
 * Created by sjk on 2017/2/8.
 */

public class BrowsingHistoryActivity extends BaseActivity implements HttpLoader.HttpListener {

    @InjectView(R.id.iv_back)
    ImageView ivBack;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_qingkong)
    TextView tvQingkong;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.lsl)
    LoadStateLayout loadStateLayout;
    @InjectView(R.id.fl_newproduct)
    LinearLayout flNewproduct;
    private BrowsingHistoryResponse browsingHistoryResponse;
    private BrowsingHistoryAdapter adapter;
    private RecordDBDao recordDBDao;
    private List<String> idlist;
    private ArrayList<BrowsingHistoryResponse> list;

    @Override
    protected int initContentView() {
        return R.layout.browsing_history_activity;
    }

    @Override
    protected void initView() {
        loadStateLayout.setEmptyView(R.layout.state_empty);
        loadStateLayout.setErrorView(R.layout.state_error);
        loadStateLayout.setLoadingView(R.layout.state_loading);
        loadStateLayout.setState(LoadStateLayout.STATE_LOADING);
        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //back按钮
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //清空按钮
        tvQingkong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idlist.size() == 0) {
                    MyToast.show(BrowsingHistoryActivity.this, "无浏览记录");
                } else {
                    recordDBDao.deleteAll();
                }
            }
        });


    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        recordDBDao = new RecordDBDao(BrowsingHistoryActivity.this);
        idlist = recordDBDao.findAll();
        if (idlist.size() > 0 && idlist != null) {
            for (int i = 0; i < idlist.size(); i++) {
                String s = idlist.get(i);
                HttpParams params = new HttpParams().put("pId", s);
                App.HL.get(URL_BROWSING_HISTORY, params, BrowsingHistoryResponse.class, REQUEST_CODE_BROWSING_HISTORY, BrowsingHistoryActivity.this);
            }
        }

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        browsingHistoryResponse = (BrowsingHistoryResponse) response;
        list.add(browsingHistoryResponse);
        //如果消息记录的集合大小和数据库获取的集合大小一致
        if (list.size() == idlist.size()) {
            if (browsingHistoryResponse.getProduct() != null) {
                if (adapter == null) {
                    //这边要把请求的数据传过去
                    lv.setAdapter(new BrowsingHistoryAdapter(list, BrowsingHistoryActivity.this));

                } else {
                    adapter.notifyDataSetChanged();
                }
                loadStateLayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
            }
        } else {
            loadStateLayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }


    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        loadStateLayout.setState(LoadStateLayout.STATE_ERROR);
        MyToast.show(getApplicationContext(), "数据请求失败！");

    }

    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }


}
