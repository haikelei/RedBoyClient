package com.itheima.redboyclient.utils;

import android.support.v4.app.Fragment;

import com.itheima.redboyclient.fragment.ClassifyFragment;
import com.itheima.redboyclient.fragment.HomeFragment;
import com.itheima.redboyclient.fragment.MoreFragment;
import com.itheima.redboyclient.fragment.SearchFragment;
import com.itheima.redboyclient.fragment.ShoppingFragment;


/**
 *
 */
public class FragmentFactory {

    public static Fragment create(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new ClassifyFragment();
                break;
            case 2:
                fragment = new SearchFragment();
                break;
            case 3:
                fragment = new ShoppingFragment();
                break;
            case 4:
                fragment = new MoreFragment();
                break;


        }
        return fragment;
    }
}
