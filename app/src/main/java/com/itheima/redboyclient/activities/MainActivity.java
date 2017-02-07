package com.itheima.redboyclient.activities;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.itheima.redboyclient.R;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by fee1in on 2017/2/7.
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.head_back_text)
    TextView headBackText;
    @InjectView(R.id.address_manager_add_text)
    TextView addressManagerAddText;
    @InjectView(R.id.title_bar)
    FrameLayout titleBar;
    @InjectView(R.id.fl_content)
    FrameLayout flContent;
    @InjectView(R.id.imgHome)
    RadioButton imgHome;
    @InjectView(R.id.imgClassify)
    RadioButton imgClassify;
    @InjectView(R.id.imgSearch)
    RadioButton imgSearch;
    @InjectView(R.id.imgShoppingCar)
    RadioButton imgShoppingCar;
    @InjectView(R.id.imgMore)
    RadioButton imgMore;
    @InjectView(R.id.linToolBar)
    RadioGroup linToolBar;

    @Override
    protected int initContentView() {
        return R.layout.activity_main;
    }


    @OnClick({R.id.imgHome, R.id.imgClassify, R.id.imgSearch, R.id.imgShoppingCar, R.id.imgMore})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgHome:
                break;
            case R.id.imgClassify:
                break;
            case R.id.imgSearch:
                break;
            case R.id.imgShoppingCar:
                break;
            case R.id.imgMore:
                break;
        }
    }
}
