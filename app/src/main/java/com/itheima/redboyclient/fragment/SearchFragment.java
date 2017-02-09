package com.itheima.redboyclient.fragment;


import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.MainActivity;
import com.itheima.redboyclient.adapter.SearchAdapter;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.net.resp.SearchTitleBean;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;



public class SearchFragment extends MainBaseFragment implements SearchAdapter.ItemOnClickListener {
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
        adapter.setItemOnClickListener(this);
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
            searchHistory.add(ConstantsRedBaby.NOHISTORY);
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
    public void OnClick(String itemName) {
        //TODO 将内容显示到搜索栏并搜索
        MyToast.show(getActivity().getApplicationContext(), itemName);
        editSearchInfo.setText(itemName);
    }
}
