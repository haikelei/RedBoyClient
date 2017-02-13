package com.itheima.redboyclient.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.OrderResponse;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Administrator on 2017/2/13.
 */
public class MyorderAdapter extends AbsBaseAdapter<OrderResponse.OrderListBean> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param context 上下文
     * @param data    要显示的数据
     */
    public MyorderAdapter(Context context, List<OrderResponse.OrderListBean> data) {
        super(context, data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyOrderHolder(parent.getContext());
    }

    static class MyOrderHolder extends BaseHolder<OrderResponse.OrderListBean> {

        @InjectView(R.id.order)
        TextView order;
        @InjectView(R.id.All_money)
        TextView AllMoney;
        @InjectView(R.id.status)
        TextView status;
        @InjectView(R.id.iv)
        ImageView iv;
        @InjectView(R.id.time)
        TextView time;

        public MyOrderHolder(Context context) {
            super(context);
        }

        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.myoreder_item, null);
            ButterKnife.inject(this, view);
            setAnim(view);
            return view;
        }

        @Override
        public void bindData(OrderResponse.OrderListBean data) {
            order.setText("订单编号 ："+data.getOrderId());
            AllMoney.setText("订单总额 ：¥"+data.getPrice());
            status.setText("状态 ："+data.getStatus());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(Integer.parseInt(data.getTime()));
            time.setText(dateFormat.format(calendar.getTime()));
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
}
