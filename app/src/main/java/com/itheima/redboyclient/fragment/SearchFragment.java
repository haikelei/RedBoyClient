package com.itheima.redboyclient.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.SearchAdapter;
import com.itheima.redboyclient.damain.SearchTitleBean;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.view.MySearchView;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class SearchFragment extends MainBaseFragment implements  HttpLoader.HttpListener, MySearchView.OnSearchListener {

    @InjectView(R.id.lv_search)
    ListView lvSearch;


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
    private String tempSearchInfo = "";
    private MySearchView mSearchView;


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
        mSearchView = new MySearchView(mActivity);
        lvSearch.addHeaderView(mSearchView);
    }

    @Override
    protected void initListener() {
        mSearchView.setOnSearchListener(this);
        adapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String itemName) {
                // 将内容显示到搜索栏并搜索
                MyToast.show(getActivity().getApplicationContext(), itemName);

                mSearchView.setText(itemName);
                //点击搜索按钮
                mSearchView.search();
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
        mSearchView.setText(tempSearchInfo);
    }

    @Override
    public void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
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

    @Override
    public void onSearch(String searchInfo) {
        refreshHistory(searchInfo);
    }

//    @OnClick(R.id.tv_search)
//    public void onItemClick() {
//        String s = editSearchInfo.getText().toString();
//        if (TextUtils.isEmpty(s)) {
//            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.edit_shake);
//            editSearchInfo.startAnimation(shake);
//            return;
//        }
//        Intent intent = new Intent(getActivity(), SearchSecondActivity.class);
//        intent.putExtra("keyword", s);
//        startActivity(intent);
//    }
}
