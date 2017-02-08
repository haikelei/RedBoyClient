package com.itheima.redboyclient.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.HomeLVAdapter;
import com.itheima.redboyclient.adapter.HomeVPAdapter;
import com.itheima.redboyclient.net.resp.HomeResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.text.BreakIterator;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "HomeFragment";
    @InjectView(R.id.editSearchInfo)
    EditText editSearchInfo;
    @InjectView(R.id.relSearch)
    RelativeLayout relSearch;
    @InjectView(R.id.vp)
    ViewPager vp;
    @InjectView(R.id.lv)
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initData();
    }

    private void initData() {
        App.HL.get(ConstantsRedBaby.URL_HOME, null, HomeResponse.class, ConstantsRedBaby.REQUEST_CODE_HOME, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {
                handleHomeResponse((HomeResponse) response);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                Log.e(TAG, "onGetResponseerror: ");
            }
        });
    }

    private void handleHomeResponse(HomeResponse response) {

        HomeVPAdapter adapter = new HomeVPAdapter(response.getHomeTopic(), getActivity());
        vp.setAdapter(adapter);
        HomeLVAdapter lvAdapter = new HomeLVAdapter();
        lv.setAdapter(lvAdapter);
        lv.setOnItemClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    //homefragment的item点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:

                break;
            case 1:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_content, new PromotionFragment()).commit();
                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;

        }

    }
}
