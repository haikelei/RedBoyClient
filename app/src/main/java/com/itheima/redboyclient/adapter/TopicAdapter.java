package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.TopicResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopicAdapter extends AbsBaseAdapter<TopicResponse.TopicEntity> {


    /**
     * 接收AbsListView要显示的数据
     *
     * @param ctx
     * @param data 要显示的数据
     */
    public TopicAdapter(Context ctx, List<TopicResponse.TopicEntity> data) {
        super(ctx, data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicViewHolder(mContext);
    }


    static class TopicViewHolder extends BaseHolder<TopicResponse.TopicEntity> {

        @InjectView(R.id.topic_TV)
        TextView mTopicTV;
        @InjectView(R.id.topic_IV)
        ImageView mTopicIV;

        public TopicViewHolder(Context context) {
            super(context);
        }


        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.topic_item, null);
            ButterKnife.inject(this,view);
            return view;
        }

        @Override
        public void bindData(TopicResponse.TopicEntity data) {
            //使用Volley的ImageLoader加载图片
            App.HL.display(mTopicIV, ConstantsRedBaby.URL_SERVER + data.pic);
            mTopicTV.setText(data.name);
        }
    }
}



