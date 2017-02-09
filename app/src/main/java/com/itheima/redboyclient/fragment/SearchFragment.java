package com.itheima.redboyclient.fragment;


import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.MainActivity;
import com.itheima.redboyclient.adapter.SearchAdapter;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;

import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends MainBaseFragment {
    @InjectView(R.id.editSearchInfo)
    EditText editSearchInfo;
    @InjectView(R.id.relSearch)
    RelativeLayout relSearch;
    @InjectView(R.id.lv_search)
    ListView lvSearch;

    private SearchAdapter adapter;
    private List<String> hotSearch;
    private List<String> searchHistory;
    private static final String TAG = "SearchFragment";

    public SearchFragment() {
        hotSearch = new ArrayList<>();
        searchHistory = new ArrayList<>();
        adapter = new SearchAdapter(hotSearch, searchHistory);
    }

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView: ");
        lvSearch.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        fillDataForSearchHistory();
    }

    private void fillDataForSearchHistory() {
        String history = ((MainActivity) mActivity).getSp().getString("searchHistory", "");
        String[] historys = history.split(",");
        searchHistory.clear();
        for (String str : historys) {
            if (!TextUtils.isEmpty(str)) {
                searchHistory.add(str);
            }
        }
        if (searchHistory.size() == 0) {
            searchHistory.add("没有搜索记录");
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void setData(IResponse response) {
        SearchRecommendResponse scr = (SearchRecommendResponse) response;
        hotSearch.clear();
        hotSearch.addAll(scr.getSearchKeywords());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

}
