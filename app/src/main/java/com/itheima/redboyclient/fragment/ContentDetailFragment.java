package com.itheima.redboyclient.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.GoodResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentDetailFragment extends Fragment {

    private static GoodResponse response;

    @InjectView(R.id.detail_container)
    LinearLayout detailContainer;

    public ContentDetailFragment() {
        // Required empty public constructor
    }


    private static ContentDetailFragment fragment = null;

    public static ContentDetailFragment newInstance(GoodResponse goodResponse) {
        response = goodResponse;
        if (fragment == null) {
            fragment = new ContentDetailFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_detail, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ArrayList<String> list = (ArrayList<String>) response.getProduct().getPics();
        for (String s : list) {
            String url = ConstantsRedBaby.URL_SERVER + s;
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            App.HL.display(imageView,url);
            detailContainer.addView(imageView);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
