package com.itheima.redboyclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.BrandActivity;
import com.itheima.redboyclient.activities.FlashActivity;
import com.itheima.redboyclient.activities.HotProductActivity;
import com.itheima.redboyclient.activities.NewProductActivity;
import com.itheima.redboyclient.activities.PromotionActivity;
import com.itheima.redboyclient.activities.SearchSecondActivity;
import com.itheima.redboyclient.adapter.HomeLVAdapter;
import com.itheima.redboyclient.adapter.HomeVPAdapter;
import com.itheima.redboyclient.net.resp.HomeResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends MainBaseFragment implements AdapterView.OnItemClickListener {
    private static final String TAG = "HomeFragment";
    @InjectView(R.id.editSearchInfo)
    EditText editSearchInfo;
    @InjectView(R.id.relSearch)
    LinearLayout relSearch;
    @InjectView(R.id.vp)
    ViewPager vp;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.tv_search)
    TextView tvSearch;

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

        /*App.HL.get(ConstantsRedBaby.URL_HOME, null, HomeResponse.class, ConstantsRedBaby.REQUEST_CODE_HOME, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {
                Log.e(TAG, "onGetResponseSuccess: "+response.toString() );
                handleHomeResponse((HomeResponse)response);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                Log.e(TAG, "onGetResponseerror: ");
            }
        });*/
    }

    private void handleHomeResponse(HomeResponse response) {
        //Log.e(TAG, "handleHomeResponse: "+response);
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
                getActivity().startActivity(new Intent(getContext(), FlashActivity.class));
                break;
            case 1:
                getActivity().startActivity(new Intent(getContext(), PromotionActivity.class));
                break;
            case 2:
                getActivity().startActivity(new Intent(getContext(), NewProductActivity.class));
                break;
            case 3:
                getActivity().startActivity(new Intent(getContext(), HotProductActivity.class));
                break;
            case 4:
                Intent intent = new Intent(getContext(), BrandActivity.class);
                startActivity(intent);
                break;

        }

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
