package com.itheima.redboyclient.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.SearchAdapter;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.damain.SearchTitleBean;
import com.itheima.redboyclient.net.resp.SearchResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


public class SearchFragment extends MainBaseFragment implements View.OnClickListener, HttpLoader.HttpListener {
    @InjectView(R.id.editSearchInfo)
    EditText editSearchInfo;
    @InjectView(R.id.relSearch)
    RelativeLayout relSearch;
    @InjectView(R.id.lv_search)
    ListView lvSearch;

    private static final String TAG = "SearchFragment";

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
        hotTitle.setShow(false);
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
        //记录搜索记录数据
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
                MyToast.show(getActivity().getApplicationContext(), "relSearch");
                String searchInfo = editSearchInfo.getText().toString().trim();
                //刷新搜索历史
                refreshHistory(searchInfo);
                if (TextUtils.isEmpty(searchInfo)) {
                    MyToast.show(getActivity().getApplicationContext(), "请输入搜索内容");
                } else {
                    //TODO跳转到搜索页面

                    String url = ConstantsRedBaby.URL_SEARCH;
                    Class clazz = SearchResponse.class;
                    int requestCode = ConstantsRedBaby.REQUEST_CODE_SEARCH;
                    HttpParams params = new HttpParams().put("keyword", searchInfo).put("page", "1").put("pageNum", "10");
                    App.HL.get(url, params, clazz, requestCode, this);
                }
                break;
        }
    }

    private void refreshHistory(String searchInfo) {
        //记录搜索
        tempSearchInfo = searchInfo;
        if (ConstantsRedBaby.NO_HISTORY.equals(searchHistory.get(0))) {
            //如果搜索记录只有搜索历史 将此条目移除
            searchHistory.remove(0);
        }
        //将搜索历史中相同的条目移除
        searchHistory.remove(searchInfo);
        //添加条目
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
}
