package com.itheima.redboyclient.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

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
public class AddAddressActivity extends BaseActivity implements HttpLoader.HttpListener {


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.add_button)
    Button addButton;
    @InjectView(R.id.receiver_tv)
    TextView receiverTv;
    @InjectView(R.id.recevier_layout)
    RelativeLayout recevierLayout;
    @InjectView(R.id.phone_tv)
    TextView phoneTv;
    @InjectView(R.id.phone_layout)
    RelativeLayout phoneLayout;
    @InjectView(R.id.province_tv)
    TextView provinceTv;
    @InjectView(R.id.province_layout)
    RelativeLayout provinceLayout;
    @InjectView(R.id.city_tv)
    TextView cityTv;
    @InjectView(R.id.city_layout)
    RelativeLayout cityLayout;
    @InjectView(R.id.area_tv)
    TextView areaTv;
    @InjectView(R.id.area_layout)
    RelativeLayout areaLayout;
    @InjectView(R.id.address_tv)
    TextView addressTv;
    @InjectView(R.id.address_layout)
    RelativeLayout addressLayout;
    @InjectView(R.id.zipcode_tv)
    TextView zipcodeTv;
    @InjectView(R.id.zipcode_layout)
    RelativeLayout zipcodeLayout;
    @InjectView(R.id.checkbox)
    CheckBox checkbox;

    @Override
    protected int initContentView() {
        return R.layout.address_add_activity;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("新增地址");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

//    @OnClick(R.id.add_button)
//    public void onClick() {
//        if (receiverTv.getText().toString() == null || phoneTv.getText().toString() == null || provinceTv.getText().toString() ==
//                null || addressTv.getText().toString() == null || areaTv.getText().toString() == null || cityTv.getText
//                ().toString() == null || zipcodeTv.getText().toString() == null) {
//            ToastUtil.showToast("地址资料未填满");
//
//        }else {
//            HttpParams params = new HttpParams().addHeader("userid", App.SP.getString("userid", null));
//            params.put("name", receiverTv.getText().toString().trim())
//                    .put("phoneNumber", phoneTv.getText().toString().trim())
//                    .put("province", provinceTv.getText().toString().trim())
//                    .put("city", cityTv.getText().toString().trim())
//                    .put("addressArea", areaTv.getText().toString().trim())
//                    .put("addressDetail", addressTv.getText().toString().trim())
//                    .put("zipCode", zipcodeTv.getText().toString().trim())
//                    .put("isDefault", checkbox.isChecked()? 1+"" : 0+"");
//            App.HL.post(ConstantsRedBaby.URL_ADDRESSSAVE, params, AddressResponse.class,
//                    ConstantsRedBaby.REQUEST_CODE_ADDRESSSAVE, this).setTag(this);
//        }
//
//    }


    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        MyToast.show(getApplicationContext(), "数据传送成功！");
        finish();
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        MyToast.show(getApplicationContext(), "数据传送失败！");
    }
}
