package com.itheima.redboyclient.fragment;


import android.support.v4.app.Fragment;

import org.senydevpkg.net.resp.IResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class MainBaseFragment extends BaseFragment {
    IResponse response;

    public void setData(IResponse response) {
        this.response = response;
    }

    public IResponse getData() {
        return response;
    }

}
