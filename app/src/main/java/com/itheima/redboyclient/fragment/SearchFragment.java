package com.itheima.redboyclient.fragment;


import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.MainActivity;
import com.itheima.redboyclient.adapter.SearchAdapter;
import com.itheima.redboyclient.net.resp.SearchRecommendResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment implements HttpLoader.HttpListener {
    @InjectView(R.id.editSearchInfo)
    EditText editSearchInfo;
    @InjectView(R.id.relSearch)
    RelativeLayout relSearch;
    @InjectView(R.id.lv_search)
    ListView lvSearch;

    private SearchAdapter adapter;
    private List<String> hotSearch;
    private List<String> searchHistory;

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initData() {
        hotSearch = new ArrayList<>();
        searchHistory = new ArrayList<>();
        adapter = new SearchAdapter(hotSearch,searchHistory);
        lvSearch.setAdapter(adapter);
        fillDataForSearchHistory();
        App.HL.get(ConstantsRedBaby.URL_SEARCH_RECOMMEND,null, SearchRecommendResponse.class,ConstantsRedBaby.REQUEST_CODE_RECOMMEND,this);

    }

    private void fillDataForSearchHistory(){
        String history = ((MainActivity) mActivity).getSp().getString("searchHistory", "");
        String[] historys = history.split(",");
        for (String str:historys) {
            if (!TextUtils.isEmpty(str)) {
            searchHistory.add(str);
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (requestCode == ConstantsRedBaby.REQUEST_CODE_RECOMMEND) {
            SearchRecommendResponse scr = (SearchRecommendResponse) response;
            hotSearch.clear();
            hotSearch.addAll(scr.getSearchKeywords());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    @Override
    public void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

}
