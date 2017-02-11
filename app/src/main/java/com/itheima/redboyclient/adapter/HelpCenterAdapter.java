package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.HelpResponse;

import org.senydevpkg.base.BaseHolder;

import java.util.List;

/**
 * Created by sjk on 2017/2/8.
 */

public class HelpCenterAdapter extends BasicAdapter<HelpResponse.HelpListBean>{
    public HelpCenterAdapter(Context mContext, List<HelpResponse.HelpListBean> data) {
        super(mContext, data);
    }



    @Override
    protected BaseHolder<HelpResponse.HelpListBean> getHolder(int position) {
        return new HelpCenterHolder(mContext);
    }

    private class HelpCenterHolder extends BaseHolder<HelpResponse.HelpListBean> {

        private TextView textView;
        private ImageView imageView;

        public HelpCenterHolder(Context context) {
            super(context);
        }

        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.item_help_center,null);
            textView = (TextView) view.findViewById(R.id.tv);

            return view;
        }

        @Override
        public void bindData(HelpResponse.HelpListBean data) {
            textView.setText(data.getTitle());
        }
    }
}
