package com.itheima.redboyclient.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.AddressResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.utils.ToastUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

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
    @InjectView(R.id.receiver_edtx)
    EditText receiverEdtx;
    @InjectView(R.id.phone_edtx)
    EditText phoneEdtx;
    @InjectView(R.id.province_edtx)
    EditText provinceEdtx;
    @InjectView(R.id.city_edtx)
    EditText cityEdtx;
    @InjectView(R.id.area_edtx)
    EditText areaEdtx;
    @InjectView(R.id.address_edtx)
    EditText addressEdtx;
    @InjectView(R.id.zipcode_edtx)
    EditText zipcodeEdtx;

    private int id;
    private int isDefault;

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
        id = getIntent().getIntExtra("id",0);
        String name = getIntent().getStringExtra("name");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String province = getIntent().getStringExtra("province");
        String city = getIntent().getStringExtra("city");
        String addressArea = getIntent().getStringExtra("addressArea");
        String addressDetail = getIntent().getStringExtra("addressDetail");
        String zipCode = getIntent().getStringExtra("zipCode");
        isDefault = getIntent().getIntExtra("isDefault",0);

        receiverEdtx.setText(name);
        phoneEdtx.setText(phoneNumber);
        provinceEdtx.setText(province);
        cityEdtx.setText(city);
        areaEdtx.setText(addressArea);
        addressEdtx.setText(addressDetail);
        zipcodeEdtx.setText(zipCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick(R.id.add_button)
    public void onClick() {
        //ToastUtil.showToast(receiverEdtx.getText().toString().trim());
        if (receiverEdtx.getText().toString().trim().equals("") || phoneEdtx.getText().toString().trim().equals("") ||
                provinceEdtx.getText().toString().trim().equals("") || addressEdtx.getText().toString().trim().equals("") || areaEdtx.getText().toString().trim().equals("") || cityEdtx.getText().toString().trim().equals("") || zipcodeEdtx.getText().toString().trim().equals("")) {
            ToastUtil.showToast("地址资料未填满");
            return;
        } else {
            HttpParams params = new HttpParams().addHeader("userid", App.SP.getString("userid",
                    null));
            if (id == 0) {
                params.put("name", receiverEdtx.getText().toString().trim())
                        .put("phoneNumber", phoneEdtx.getText().toString().trim())
                        .put("province", provinceEdtx.getText().toString().trim())
                        .put("city", cityEdtx.getText().toString().trim())
                        .put("addressArea", areaEdtx.getText().toString().trim())
                        .put("addressDetail", addressEdtx.getText().toString().trim())
                        .put("zipCode", zipcodeEdtx.getText().toString().trim())
                        .put("isDefault", 0 + "");
            }else {
                params.put("id",id + "")
                        .put("name", receiverEdtx.getText().toString().trim())
                        .put("phoneNumber", phoneEdtx.getText().toString().trim())
                        .put("province", provinceEdtx.getText().toString().trim())
                        .put("city", cityEdtx.getText().toString().trim())
                        .put("addressArea", areaEdtx.getText().toString().trim())
                        .put("addressDetail", addressEdtx.getText().toString().trim())
                        .put("zipCode", zipcodeEdtx.getText().toString().trim())
                        .put("isDefault", isDefault + "");
            }

            App.HL.post(ConstantsRedBaby.URL_ADDRESSSAVE, params, AddressResponse.class,
                    ConstantsRedBaby.REQUEST_CODE_ADDRESSSAVE, this).setTag(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        MyToast.show(getApplicationContext(), "数据传送成功！");
        finish();
        //handleTopicResponse((AddressResponse) response);
    }


    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        MyToast.show(getApplicationContext(), "数据传送失败！");
    }

}
