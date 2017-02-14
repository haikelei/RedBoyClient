package com.itheima.redboyclient.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.SearchSecondActivity;
import com.itheima.redboyclient.adapter.SearchAdapter;
import com.itheima.redboyclient.damain.SearchTitleBean;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class SearchFragment extends MainBaseFragment implements View.OnClickListener, HttpLoader.HttpListener {
    @InjectView(R.id.relSearch)
    LinearLayout relSearch;
    @InjectView(R.id.lv_search)
    ListView lvSearch;

    private static final String TAG = "SearchFragment";
    @InjectView(R.id.editSearchInfo)
    TextInputEditText editSearchInfo;
    @InjectView(R.id.tv_search)
    TextView tvSearch;

    private SearchAdapter adapter;
    //热门搜索
    private List<String> hotSearch;
    //搜索历史
    private List<String> searchHistory;

    //热门搜索标题
    private SearchTitleBean hotTitle;
    //搜索历史标题
    private SearchTitleBean historyTitle;

    private SharedPreferences.Editor edit;
    private SharedPreferences sp;
    //临时记录搜索条目
    private String tempSearchInfo;


    public SearchFragment() {
        hotSearch = new ArrayList<>();
        searchHistory = new ArrayList<>();

        //热门搜索条目默认不显示
        hotTitle = new SearchTitleBean();
        hotTitle.setTitle("热门搜索");
        hotTitle.setShow(true);
        //搜索历史条目默认显示
        historyTitle = new SearchTitleBean();
        historyTitle.setTitle("搜索历史");
        historyTitle.setShow(true);

        adapter = new SearchAdapter(hotTitle, hotSearch, historyTitle, searchHistory);
        sp = App.SP;
        edit = App.EDIT;
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_search;
    }


    @Override
    protected void initView() {
        lvSearch.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        relSearch.setOnClickListener(this);
        adapter.setItemOnClickListener(new SearchAdapter.ItemOnClickListener() {
            @Override
            public void onClick(String itemName) {
                // 将内容显示到搜索栏并搜索
                MyToast.show(getActivity().getApplicationContext(), itemName);
                editSearchInfo.setText(itemName);
                //将光标焦点移到最后
                editSearchInfo.setSelection(itemName.length());
                //点击搜索按钮
                SearchFragment.this.onClick(relSearch);
            }
        });


    }

    protected void initData() {
        IResponse data = getData();
        SearchRecommendResponse scr = (SearchRecommendResponse) data;
        //记录热门搜索数据
        hotSearch.clear();
        hotSearch.addAll(scr.getSearchKeywords());

        //填充搜索历史数据
        fillDataForSearchHistory();

        //刷新数据
        adapter.refresh();
    }


    private void fillDataForSearchHistory() {
        String localHistory = sp.getString("searchHistory", "");
        String[] historyNames = localHistory.split(",");
        //重新加载搜索记录
        searchHistory.clear();
        for (String historyName : historyNames) {
            if (!TextUtils.isEmpty(historyName)) {
                searchHistory.add(historyName);
            }
        }

        if (searchHistory.size() == 0) {
            //没有搜索记录,设置默认显示
            searchHistory.add(ConstantsRedBaby.NO_HISTORY);
        }
        //刷新数据
        adapter.refresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        //回显搜索记录
        editSearchInfo.setText(tempSearchInfo);
    }

    @Override
    public void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relSearch:
                String searchInfo = editSearchInfo.getText().toString().trim();
                //刷新搜索历史
                refreshHistory(searchInfo);
                if (TextUtils.isEmpty(searchInfo)) {
                    MyToast.show(getActivity().getApplicationContext(), "请输入搜索内容");
                } else {
                    //TODO跳转到搜索页面lujialei
                    Log.e(TAG, "onClick: " + searchInfo);
                    Intent intent = new Intent(getActivity(), SearchSecondActivity.class);
                    intent.putExtra("keyword", searchInfo);
                    startActivity(intent);
                    /*String url = ConstantsRedBaby.URL_SEARCH;
                    Class clazz = SearchResponse.class;
                    int requestCode = ConstantsRedBaby.REQUEST_CODE_SEARCH;
                    HttpParams params = new HttpParams().put("keyword", searchInfo).put("page", "1").put("pageNum", "10");
                    App.HL.get(url, params, clazz, requestCode, this);*/
                }
                break;
        }
    }

    private void refreshHistory(String searchInfo) {
        //记录搜索
        tempSearchInfo = searchInfo;
        if (ConstantsRedBaby.NO_HISTORY.equals(searchHistory.get(0))) {
            //如果搜索记录第一条是 没有搜索历史 将此条目移除
            searchHistory.remove(0);
        }
        //将搜索历史中已经包含的条目移除
        searchHistory.remove(searchInfo);
        //将最新的搜索历史加到第一行
        searchHistory.add(0, searchInfo);
        //将搜索历史中的条目拼接好，存入sp
        StringBuilder localHistory = new StringBuilder();
        for (String history : searchHistory) {
            localHistory.append(history).append(",");
        }
        edit.putString("searchHistory", localHistory.toString());
        edit.commit();
        //刷新数据
        adapter.refresh();
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == ConstantsRedBaby.REQUEST_CODE_SEARCH) {
            Intent intent = new Intent();
            Serializable serializable = (Serializable) response;
            intent.putExtra("info", serializable);
            mActivity.startActivity(intent);
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        if (requestCode == ConstantsRedBaby.REQUEST_CODE_SEARCH) {
            MyToast.show(mActivity.getApplicationContext(), "数据请求失败！");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.tv_search)
    public void onClick() {
        String s = editSearchInfo.getText().toString();
        if(TextUtils.isEmpty(s)){
            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.edit_shake);
            editSearchInfo.startAnimation(shake);
            return;
        }
        Intent intent = new Intent(getActivity(), SearchSecondActivity.class);
        intent.putExtra("keyword",s);
        startActivity(intent);
    }
}
