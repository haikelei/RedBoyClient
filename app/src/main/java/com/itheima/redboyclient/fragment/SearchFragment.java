package com.itheima.redboyclient.fragment;


import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.MainActivity;
import com.itheima.redboyclient.adapter.SearchAdapter;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.net.resp.SearchTitleBean;

import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;



public class SearchFragment extends MainBaseFragment implements AdapterView.OnItemClickListener {
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
    private String NOHISTORY = "没有搜索记录";;

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
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initListener() {
        Log.i(TAG, "initListener: ");
        lvSearch.setOnItemClickListener(this);
    }

    @Override
    protected void initView() {
        lvSearch.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        fillDataForSearchHistory();
    }

    private void fillDataForSearchHistory() {
        String localHistory = ((MainActivity) mActivity).getSp().getString("searchHistory", "");
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
            searchHistory.add(NOHISTORY);
        }
        //刷新数据
        adapter.initData();
    }


    @Override
    public void setData(IResponse response) {
        SearchRecommendResponse scr = (SearchRecommendResponse) response;
        //记录热门搜索数据
        hotSearch.clear();
        hotSearch.addAll(scr.getSearchKeywords());
        //刷新数据
        adapter.initData();

    }

    @Override
    public void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: ");
        if (position == 0) {
            //改变热门搜索的显示状态，刷新页面
            boolean isShow = hotTitle.isShow();
            hotTitle.setShow(!isShow);
            adapter.initData();

        } else if (position == hotSearch.size() + 1) {
            //改变搜索历史的显示状态，刷新页面
            boolean isShow = historyTitle.isShow();
            historyTitle.setShow(!isShow);
            adapter.initData();
        } else {
            //获得点击条目的内容
            String itemName = adapter.getItemName(view);
            MyToast.show(getActivity().getApplicationContext(), itemName);
            //将点击条目与sp保存的历史纪录拼接，存入sp
            SharedPreferences sp = ((MainActivity) mActivity).getSp();
            SharedPreferences.Editor edit = ((MainActivity) mActivity).getEdit();
            String localHistory = sp.getString("searchHistory", "");
            localHistory = itemName + "," + localHistory;
            edit.putString("searchHistory",localHistory);
            edit.commit();
            if (NOHISTORY.equals(searchHistory.get(0))) {
                searchHistory.remove(0);
            }
            searchHistory.add(0,itemName);
            adapter.initData();

            //TODO 将内容显示到搜索栏并搜索
        }
    }


}
