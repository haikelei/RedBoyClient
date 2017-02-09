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

/**
 * Created by Administrator on 2017/2/8.
 */
public class PromotionAdapter extends AbsBaseAdapter<TopicResponse.TopicEntity> {

    /**
     * 接收AbsListView要显示的数据
     *
     * @param context 上下文
     * @param data    要显示的数据
     */
    public PromotionAdapter(Context context, List<TopicResponse.TopicEntity> data) {
        super(context, data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PromotionViewHolder(mContext);
    }

    static class PromotionViewHolder extends BaseHolder<TopicResponse.TopicEntity> {
        @InjectView(R.id.pro_TV)
        TextView mProTV;
        @InjectView(R.id.pro_IV)
        ImageView mProIV;

        public PromotionViewHolder(Context context) {
            super(context);
        }

        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.promotion_item, null);
            ButterKnife.inject(this, view);
            return view;
        }

        @Override
        public void bindData(TopicResponse.TopicEntity data) {
            //使用Volley的ImageLoader加载图片
            App.HL.display(mProIV, ConstantsRedBaby.URL_SERVER + data.pic);
            mProTV.setText(data.name);
        }
    }
}
