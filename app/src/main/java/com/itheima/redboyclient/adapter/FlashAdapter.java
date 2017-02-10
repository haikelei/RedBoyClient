package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.FlashResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.base.AbsBaseAdapter;
import org.senydevpkg.base.BaseHolder;

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
            tv2.setText("¥ :" + data.getPrice());
            tv3.setText("限时特价 :" + data.getLimitPrice());
            tv3.setTextColor(Color.RED);
            tv4.setText("剩余时间 :" + toTimeStr(Integer.parseInt(data.getLeftTime())));

        }
    }
}
