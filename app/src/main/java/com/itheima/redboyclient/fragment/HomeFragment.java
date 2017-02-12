package com.itheima.redboyclient.fragment;


import android.content.Intent;
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

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.BrandActivity;
import com.itheima.redboyclient.activities.FlashActivity;
import com.itheima.redboyclient.activities.HotProductActivity;
import com.itheima.redboyclient.activities.NewProductActivity;
import com.itheima.redboyclient.activities.PromotionActivity;
import com.itheima.redboyclient.adapter.HomeLVAdapter;
import com.itheima.redboyclient.adapter.HomeVPAdapter;
import com.itheima.redboyclient.net.resp.HomeResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends MainBaseFragment implements AdapterView.OnItemClickListener {
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

    protected void initData() {
        HomeResponse response = (HomeResponse) getData();
        handleHomeResponse(response);
    }

    private void handleHomeResponse(HomeResponse response) {

        HomeVPAdapter adapter = new HomeVPAdapter(response.getHomeTopic(),getActivity());
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
        switch (position){
            case 0:
                getActivity().startActivity(new Intent(getContext(),FlashActivity.class));
                break;
            case 1:
                getActivity().startActivity(new Intent(getContext(),PromotionActivity.class));
                break;
            case 2:
                getActivity().startActivity(new Intent(getContext(),NewProductActivity.class));
                break;
            case 3:
                getActivity().startActivity(new Intent(getContext(),HotProductActivity.class));
                break;
            case 4:
                Intent intent = new Intent(getContext(), BrandActivity.class);
                startActivity(intent);
                break;

        }

    }
}
