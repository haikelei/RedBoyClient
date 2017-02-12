package com.itheima.redboyclient.adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.FlashResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/2/10.
 */
public class FlashAdapter extends AbsBaseAdapter<FlashResponse.ProductListBean> {


    /**
     * 接收AbsListView要显示的数据
     *
     * @param context 上下文
     * @param data    要显示的数据
     */
    public FlashAdapter(Context context, List<FlashResponse.ProductListBean> data) {
        super(context, data);
    }

    @Override
    protected BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FlashViewHolder(parent.getContext());
    }

    static class FlashViewHolder extends BaseHolder<FlashResponse.ProductListBean> {
        ImageView iv;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;
        TextView flash;

        public FlashViewHolder(Context context) {
            super(context);
        }

        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.flash_item, null);
            iv = (ImageView) view.findViewById(R.id.iv);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            tv3 = (TextView) view.findViewById(R.id.tv3);
            tv4 = (TextView) view.findViewById(R.id.tv4);
            flash = (TextView) view.findViewById(R.id.flash);
            setAnim(view);
            return view;
        }

        private String to2Str(int i) {

            if (i > 9) {

                return i + "";
            } else {

                return "0" + i;
            }

        }

        private String toTimeStr(int secTotal) {

            String result = null;
//            secTotal = secTotal / 1000;
            int hour = secTotal / 3600;
            int min = (secTotal % 3600) / 60;
            int sec = (secTotal % 3600) % 60;
            result = to2Str(hour) + ":" + to2Str(min) + ":" + to2Str(sec);

            return result;
        }

        @Override
        public void bindData(FlashResponse.ProductListBean data) {

            App.HL.display(iv, ConstantsRedBaby.URL_SERVER + data.getPic());
            tv1.setText(data.getName());
            tv2.setText("¥ : " + data.getPrice());
            tv2.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            tv2.getPaint().setAntiAlias(true);// 抗锯齿
            tv3.setText("限时特价 : ¥ " + data.getLimitPrice());
            tv3.setTextColor(Color.RED);
            ValueAnimator valueAnimator = ValueAnimator.ofInt(Integer.parseInt(data.getLeftTime()), 0);
            valueAnimator.setDuration(Integer.parseInt(data.getLeftTime()) * 1000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int value = (int) valueAnimator.getAnimatedValue();

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                    Date date1 = new Date(value * 1000);
                    String timeStr = simpleDateFormat.format(date1);
                    tv4.setText(timeStr + "");
                }
            });
            valueAnimator.start();


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
