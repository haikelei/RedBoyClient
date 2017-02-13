package com.itheima.redboyclient.Anim;

import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by Administrator on 2017/2/13 0013.
 */


    public abstract class BaseAnim {
        protected ValueAnimator animator = null;
        protected View target;//执行动画的目标view
        public BaseAnim(final View target,int startValue,int endValue){
            this.target = target;

            animator = ValueAnimator.ofInt(startValue,endValue);
            //应该根据动画更新的进度，自己实现动画的逻辑
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    int animatedValue = (Integer) animator.getAnimatedValue();
                    //执行具体的动画逻辑
                    doAnim(animatedValue);
                }
            });
        }
        /**
         * 开启动画
         * @param duration
         */
        public void start(int duration){
            animator.setDuration(duration).start();
        }
        /**
         * 执行具体的动画行为，由每个子类去实现
         * @param animatedValue
         */
        protected abstract void doAnim(int animatedValue);
    }


