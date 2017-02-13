package com.itheima.redboyclient.activities;


import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.BookmarkAdapter;
import com.itheima.redboyclient.net.resp.BookmarkResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import butterknife.InjectView;

/**
 * Created by Administrator on 2017/2/12.
 */
public class BookmarksActivity extends BaseActivity implements HttpLoader.HttpListener {

    private static final String TAG = "BookmarksActivity";
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.listview)
    ListView listview;
    @InjectView(R.id.loadstatelayout)
    LoadStateLayout loadstatelayout;

    private BookmarkAdapter adapter;
    private BookmarkResponse topics;

    @Override
    protected int initContentView() {
        return R.layout.bookmark_activity;
    }

    @Override
    protected void initView() {
        initToolBar();
        initData();
    }

    private void initToolBar() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        loadstatelayout.setEmptyView(R.layout.state_empty);
        loadstatelayout.setErrorView(R.layout.state_error);
        loadstatelayout.setLoadingView(R.layout.state_loading);
        loadstatelayout.setState(LoadStateLayout.STATE_LOADING);

        HttpParams params = new HttpParams().addHeader("userid", App.SP.getString("userid",null))
                .put("page", "0").put("pageNum", "10");

        App.HL.get(ConstantsRedBaby.URL_BOOKMARK, params, BookmarkResponse.class, ConstantsRedBaby.REQUEST_CODE_BOOKMARK, this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleTopicResponse((BookmarkResponse) response);
    }

    /**
     * 处理Topic专题数据的展示
     *
     * @param response
     */
    private void handleTopicResponse(BookmarkResponse response) {
        topics = response;
        if (topics.getProductList() != null && topics.getProductList().size() > 0) {
            if (adapter == null) {

                adapter = new BookmarkAdapter(this, topics.getProductList());
                listview.setAdapter(adapter);

            } else {

                adapter.notifyDataSetChanged(topics.getProductList());
            }
            loadstatelayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
        } else {

            loadstatelayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        if (adapter == null)//如果当前没有设置过数据
            loadstatelayout.setState(LoadStateLayout.STATE_ERROR);//显示请求错误的View

        MyToast.show(this, "数据请求失败！");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.HL.cancelRequest(this);
    }
}
