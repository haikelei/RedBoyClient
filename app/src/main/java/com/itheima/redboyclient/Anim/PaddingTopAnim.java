package com.itheima.redboyclient.Anim;

import android.view.View;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class PaddingTopAnim extends BaseAnim {
    public PaddingTopAnim(View target, int startValue, int endValue) {
        super(target, startValue, endValue);
    }

    @Override
    protected void doAnim(int animatedValue) {
        //将动画的值设置为view的当前的paddingTop
        target.setPadding(target.getPaddingLeft(),animatedValue,target.getPaddingRight()
                , target.getPaddingBottom());
    }
}
