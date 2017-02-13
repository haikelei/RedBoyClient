package com.itheima.redboyclient.adapter;


import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.BookmarkResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/2/12.
 */
public class BookmarkAdapter extends AbsBaseAdapter<BookmarkResponse.ProductListBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param context 上下文
     * @param data    要显示的数据
     */
    public BookmarkAdapter(Context context, List<BookmarkResponse.ProductListBean> data) {
        super(context, data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookmarkHolder(parent.getContext());
    }

    static class BookmarkHolder extends BaseHolder<BookmarkResponse.ProductListBean> {

        @InjectView(R.id.iv_pic)
        ImageView ivPic;
        @InjectView(R.id.tv_name)
        TextView tvName;
        @InjectView(R.id.market_price)
        TextView marketPrice;
        @InjectView(R.id.tv_price)
        TextView tvPrice;
        @InjectView(R.id.pinglun)
        TextView pinglun;
        @InjectView(R.id.ll_item)
        LinearLayout llItem;
        @InjectView(R.id.iv)
        ImageView iv;

        public BookmarkHolder(Context context) {
            super(context);
        }

        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.bookmark_item, null);
            ButterKnife.inject(this,view);
            setAnim(view);
            return view;
        }

        @Override
        public void bindData(BookmarkResponse.ProductListBean data) {
            App.HL.display(ivPic, ConstantsRedBaby.URL_SERVER + data.getPic());
            tvName.setText(data.getName());
            marketPrice.setText("¥ :" +data.getMarketPrice());
            marketPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG);
            marketPrice.getPaint().setAntiAlias(true);// 抗锯齿
            tvPrice.setText("¥ :" +data.getPrice());
            pinglun.setText("已经有...人评价");
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
