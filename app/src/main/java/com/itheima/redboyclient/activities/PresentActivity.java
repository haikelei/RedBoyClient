package com.itheima.redboyclient.activities;

import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PresentActivity extends BaseActivity {
    @InjectView(R.id.back)
    Button back;
    @InjectView(R.id.add)
    Button add;
    @InjectView(R.id.ctv1)
    CheckedTextView ctv1;
    @InjectView(R.id.ctv2)
    CheckedTextView ctv2;
    @InjectView(R.id.ctv3)
    CheckedTextView ctv3;
    @InjectView(R.id.activity_present)
    LinearLayout activityPresent;
    boolean ctv1Checked = false;
    boolean ctv2Checked = false;
    boolean ctv3Checked = false;

    @Override
    protected int initContentView() {
        return R.layout.activity_present;
    }




    @Override
    protected void initView() {
        ButterKnife.inject(this);
        ctv1Checked = App.SP.getBoolean("ctv1Checked",false);
        ctv2Checked = App.SP.getBoolean("ctv2Checked",false);
        ctv3Checked = App.SP.getBoolean("ctv3Checked",false);
        checked();
    }

    @OnClick({R.id.back, R.id.add,R.id.ctv1, R.id.ctv2, R.id.ctv3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:

                break;
            case R.id.add:
                break;
            case R.id.ctv1:

                ctv1Checked = true;
                ctv2Checked = false;
                ctv3Checked = false;
                App.EDIT.putBoolean("ctv1Checked",ctv1Checked).apply();
                App.EDIT.putBoolean("ctv2Checked",ctv2Checked).apply();
                App.EDIT.putBoolean("ctv3Checked",ctv3Checked).apply();
                checked();
                break;
            case R.id.ctv2:
                ctv2Checked = true;
                ctv1Checked = false;
                ctv3Checked = false;
                App.EDIT.putBoolean("ctv1Checked",ctv1Checked).apply();
                App.EDIT.putBoolean("ctv2Checked",ctv2Checked).apply();
                App.EDIT.putBoolean("ctv3Checked",ctv3Checked).apply();
                checked();
                break;
            case R.id.ctv3:
                ctv3Checked = true;
                ctv2Checked = false;
                ctv1Checked = false;
                App.EDIT.putBoolean("ctv1Checked",ctv1Checked).apply();
                App.EDIT.putBoolean("ctv2Checked",ctv2Checked).apply();
                App.EDIT.putBoolean("ctv3Checked",ctv3Checked).apply();
                checked();
                break;
        }
    }

    public void checked(){

        if (ctv1Checked){
            ctv1.setChecked(true);
        }else {
            ctv1.setChecked(false);
        }
        if (ctv2Checked){
            ctv2.setChecked(true);
        }else {
            ctv2.setChecked(false);
        }
        if (ctv3Checked){
            ctv3.setChecked(true);
        }else {
            ctv3.setChecked(false);
        }
    }
}
