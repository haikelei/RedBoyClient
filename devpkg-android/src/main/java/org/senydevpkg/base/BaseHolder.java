package org.senydevpkg.base;

import android.content.Context;
import android.view.View;

import org.springframework.util.Assert;


public abstract class BaseHolder<T> {

    public final View rootView;
    private final Context mContext;

    public BaseHolder(Context context) {
        Assert.notNull(context);
        mContext = context;
        rootView = initView();
        rootView.setTag(this);
    }

    /**
     * 获取上下文
     *
     * @return 上下文对象
     */
    public Context getContext() {
        return mContext;
    }

    protected abstract View initView();

    /**
     * 给Holder管理的View设置数据
     *
     * @param data 数据
     */
    public void bindData(T data) {

    }
    public View getHolderView() {
        return rootView;
    }


}
