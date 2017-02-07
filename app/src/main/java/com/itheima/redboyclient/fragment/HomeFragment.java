package com.itheima.redboyclient.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by gary on 2017/2/7.
 */

public class HomeFragment extends Fragment {

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
                handleHomeResponse((HomeResponse)response);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                Log.e(TAG, "onGetResponseerror: ");
            }
        });
    }

    private void handleHomeResponse(HomeResponse response) {

        HomeVPAdapter adapter = new HomeVPAdapter(response.getHomeTopic(),getActivity());
        vp.setAdapter(adapter);
        HomeLVAdapter lvAdapter = new HomeLVAdapter();
        lv.setAdapter(lvAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
