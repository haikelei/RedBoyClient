package com.itheima.redboyclient.utils;

import com.itheima.redboyclient.fragment.ClassifyFragment;
import com.itheima.redboyclient.fragment.HomeFragment;
import com.itheima.redboyclient.fragment.MainBaseFragment;
import com.itheima.redboyclient.fragment.MoreFragment;
import com.itheima.redboyclient.fragment.SearchFragment;
import com.itheima.redboyclient.fragment.ShoppingFragment;


/**
 *
 */
public class FragmentFactory {
    private static HomeFragment homeFragment;
    private static SearchFragment searchFragment;
    private static ClassifyFragment classifyFragment;
    private static ShoppingFragment shoppingFragment;
    private static MoreFragment moreFragment;

    public static MainBaseFragment getFragment(int position) {
        MainBaseFragment baseFragment = null;
        switch (position) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                }
                baseFragment = homeFragment;
                break;
            case 1:
                if (searchFragment == null) {
                    searchFragment = new SearchFragment();
                }
                baseFragment = searchFragment;
                break;
            case 2:
                if (classifyFragment == null) {
                    classifyFragment = new ClassifyFragment();
                }
                baseFragment = classifyFragment;
                break;
            case 3:
                if (shoppingFragment == null) {
                    shoppingFragment = new ShoppingFragment();
                }
                baseFragment = shoppingFragment;
                break;
            case 4:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();
                }
                baseFragment = moreFragment;
                break;
        }
        return baseFragment;
    }
}
