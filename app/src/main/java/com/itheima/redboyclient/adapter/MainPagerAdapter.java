package com.itheima.redboyclient.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.itheima.redboyclient.utils.FragmentFactory;


/**
 * Created by fee1in on 2017/1/3.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private int size = 5;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.create(position);
    }

    @Override
    public int getCount() {
        return size;
    }


}
