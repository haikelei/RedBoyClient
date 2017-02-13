package com.itheima.redboyclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
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

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
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
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends MainBaseFragment implements AdapterView.OnItemClickListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private static final String TAG = "HomeFragment";
    @InjectView(R.id.editSearchInfo)
    EditText editSearchInfo;
    @InjectView(R.id.relSearch)
    LinearLayout relSearch;
    @InjectView(R.id.lv)
    ListView lv;
    @InjectView(R.id.tv_search)
    TextView tvSearch;
    @InjectView(R.id.slider)
    SliderLayout slider;
    @InjectView(R.id.custom_indicator)
    PagerIndicator customIndicator;

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
        ArrayList<HomeResponse.HomeTopicBean> beans = (ArrayList<HomeResponse.HomeTopicBean>) response.getHomeTopic();
        HashMap<String,String> urlMap = new HashMap<String, String>();
        for (HomeResponse.HomeTopicBean bean: beans) {
            urlMap.put(bean.getTitle()+bean.getId(), ConstantsRedBaby.URL_SERVER+bean.getPic());
        }


        for(String name : urlMap.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(urlMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            slider.addSlider(textSliderView);
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());
        slider.setDuration(4000);
        slider.addOnPageChangeListener(this);

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
        if (TextUtils.isEmpty(s)) {
            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.edit_shake);
            editSearchInfo.startAnimation(shake);
            return;
        }
        Intent intent = new Intent(getActivity(), SearchSecondActivity.class);
        intent.putExtra("keyword", s);
        startActivity(intent);
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
