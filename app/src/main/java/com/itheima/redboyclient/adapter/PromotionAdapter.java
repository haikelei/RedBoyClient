package com.itheima.redboyclient.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
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
            setAnim(view);
            return view;
        }

        @Override
        public void bindData(TopicResponse.TopicEntity data) {
            //使用Volley的ImageLoader加载图片
            App.HL.display(mProIV, ConstantsRedBaby.URL_SERVER + data.pic);
            mProTV.setText(data.name);
        }
    }
    public  static void setAnim(final View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0.5f, 1.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float value = (Float) valueAnimator.getAnimatedValue();
                view.setScaleX(value);
                view.setScaleY(value);
            }
        });
        animator.setDuration(400);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }
}
