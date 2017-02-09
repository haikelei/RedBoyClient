package com.itheima.redboyclient.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    private View rootView;

public class BaseFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = getActivity();
        rootView = inflater.inflate(getRootViewId(), container, false);
        ButterKnife.inject(this, rootView);
        initView();
        initListener();
        initData();
        return rootView;
        TextView textView = new TextView(getActivity());
        textView.setText(this.getClass().getSimpleName());
        initData();
        return textView;
    }

    protected void initData() {
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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
    private void initData() {
    }



}
