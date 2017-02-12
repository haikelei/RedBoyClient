package com.itheima.redboyclient.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();
        rootView = inflater.inflate(getRootViewId(), container, false);
        ButterKnife.inject(this, rootView);
        initView();
        initListener();

        return rootView;
    }


    protected void initListener() {
    }

    protected void initView() {

    }

    protected int getRootViewId() {
        return 0;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mActivity = null;
        ButterKnife.reset(this);
    }

}



