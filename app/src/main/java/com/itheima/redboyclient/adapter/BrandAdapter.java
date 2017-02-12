package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.BrandResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.base.BaseHolder;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　()
 * 　　  ( ) 　　( )
 * 　　  ( ) 　　( )
 * 　　┏━┛┻━━━━━━┛┻┓
 * 　　┃　　　━　　 ┃
 * 　　┃　┳┛　  ┗┳ ┃
 * 　　┃　　　┻　　 ┃
 * 　　┗━━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┗━━┣┓
 * 　　　　┃　　　　┏━━━━┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 */
public class BrandAdapter extends BasicAdapter<Object> {
    /**
     * 接收AbsListView要显示的数据
     *
     * @param context 上下文
     * @param data    要显示的数据
     */

    public final int ITEM_TITLE = 0;//title类型的条目
    public final int ITEM_INFO = 1;//info类型的条目

    public BrandAdapter(Context mContext, List<Object> data) {
        super(mContext, data);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Object object = mData.get(position);
        if (object instanceof String) {
            return ITEM_TITLE;
        } else {
            return ITEM_INFO;
        }
    }

    @Override
    protected BaseHolder<Object> getHolder(int position) {
        BaseHolder<Object> baseHolder = null;
        int itemType = getItemViewType(position);
        if (itemType == ITEM_TITLE) {
            baseHolder = new BrandTitleHolder(mContext);
        }else {
            baseHolder = new BrandInfoHolder(mContext);
        }

        return baseHolder;
    }

    static class BrandTitleHolder extends BaseHolder {

        @InjectView(R.id.brand_TV)
        TextView brandTV;

        public BrandTitleHolder(Context context) {
            super(context);
        }

        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.brand_items_title, null);
            ButterKnife.inject(this, view);
            return view;
        }

        @Override
        public void bindData(Object data) {
            String key = (String) data;
            brandTV.setText(key);
        }
    }

    static class BrandInfoHolder extends BaseHolder {

        @InjectView(R.id.nineGv)
        GridView nineGv;

        public BrandInfoHolder(Context context) {
            super(context);

        }

        @Override
        protected View initView() {
            View view = View.inflate(getContext(), R.layout.brand_items, null);
            ButterKnife.inject(this, view);
            return view;
        }

        @Override
        public void bindData(final Object data) {
            final List<BrandResponse.BrandBean.ValueBean> valueBeanList = (List<BrandResponse.BrandBean.ValueBean>) data;
            nineGv.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return valueBeanList.size();
                }

                @Override
                public Object getItem(int position) {
                    return null;
                }

                @Override
                public long getItemId(int position) {
                    return 0;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = null;
                    if (convertView == null) {
                        view = View.inflate(getContext(),R.layout.brand_info_pic,null);
                    }else {
                        view = convertView;
                    }
                    ImageView imageView = (ImageView) view.findViewById(R.id.image);
                    App.HL.display(imageView, ConstantsRedBaby.URL_SERVER + valueBeanList.get(position).getPic());
                    return view;
                }
            });
        }
    }

}
