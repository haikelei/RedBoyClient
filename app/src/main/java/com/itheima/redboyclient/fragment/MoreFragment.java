package com.itheima.redboyclient.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.AboutRellayActivity;
import com.itheima.redboyclient.activities.AccountCenterActivity;
import com.itheima.redboyclient.activities.BrowsingHistoryActivity;
import com.itheima.redboyclient.activities.FeedBackActivity;
import com.itheima.redboyclient.activities.HelpCenterActivity;
import com.itheima.redboyclient.activities.LoginActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends MainBaseFragment {


    @InjectView(R.id.my_account_rl)
    RelativeLayout myAccountRl;
    @InjectView(R.id.recent_browse_rl)
    RelativeLayout recentBrowseRl;
    @InjectView(R.id.helpRelLay)
    RelativeLayout helpRelLay;
    @InjectView(R.id.user_feedback)
    RelativeLayout userFeedback;
    @InjectView(R.id.aboutRelLay)
    RelativeLayout aboutRelLay;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_activity, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.my_account_rl, R.id.recent_browse_rl, R.id.helpRelLay, R.id.user_feedback, R.id.aboutRelLay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_account_rl:
                // TODO: 2017/2/10  开启账户中心的activity
                boolean islogin = App.SP.getBoolean("islogin",false);
                if (islogin) {
                    startActivity(new Intent(getActivity(), AccountCenterActivity.class));
                } else {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
                break;
            case R.id.recent_browse_rl:
                startActivity(new Intent(getActivity(), BrowsingHistoryActivity.class));
                break;
            case R.id.helpRelLay:
                startActivity(new Intent(getActivity(),HelpCenterActivity.class));
                break;
            case R.id.user_feedback:
                startActivity(new Intent(getActivity(),FeedBackActivity.class));
                break;
            case R.id.aboutRelLay:
                startActivity(new Intent(getActivity(),AboutRellayActivity.class));
                break;
        }
    }
}
