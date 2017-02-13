package com.itheima.redboyclient.activities;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.Anim.PaddingTopAnim;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.ProductDetailAdapter;
import com.itheima.redboyclient.net.resp.SettlemetCenterProductDetailResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.view.NoScrollListView;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SettlementCenterActivity extends BaseActivity implements HttpLoader.HttpListener {

    @InjectView(R.id.shoppingcar)
    Button shoppingcar;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.submitorder)
    Button submitorder;
    @InjectView(R.id.rl_usermsg)
    RelativeLayout person;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_pay)
    TextView tvPay;
    @InjectView(R.id.payment_payValue_text)
    TextView paymentPayValueText;
    @InjectView(R.id.pay_rb1)
    RadioButton payRb1;
    @InjectView(R.id.pay_rb2)
    RadioButton payRb2;
    @InjectView(R.id.pay_rb3)
    RadioButton payRb3;
    @InjectView(R.id.rl_pay)
    RelativeLayout rlPay;
    @InjectView(R.id.tv_time)
    TextView tvTime;
    @InjectView(R.id.payment_sendTimeValue_text)
    TextView paymentSendTimeValueText;
    @InjectView(R.id.time_rb1)
    RadioButton timeRb1;
    @InjectView(R.id.time_rb2)
    RadioButton timeRb2;
    @InjectView(R.id.time_rb3)
    RadioButton timeRb3;
    @InjectView(R.id.rg_time)
    RadioGroup rgTime;
    @InjectView(R.id.rl_time)
    RelativeLayout rlTime;
    @InjectView(R.id.tv_deliverymode)
    TextView tvDeliverymode;
    @InjectView(R.id.mode_rb1)
    RadioButton modeRb1;
    @InjectView(R.id.mode_rb2)
    RadioButton modeRb2;
    @InjectView(R.id.mode_rb3)
    RadioButton modeRb3;
    @InjectView(R.id.rg_mode)
    RadioGroup rgMode;
    @InjectView(R.id.rl_deliverymode)
    RelativeLayout rlDeliverymode;
    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.coupon)
    RelativeLayout coupon;
    @InjectView(R.id.tv_coupon)
    TextView tvCoupon;
    @InjectView(R.id.invoice)
    RelativeLayout invoice;
    @InjectView(R.id.goodsdetail)
    TextView goodsdetail;
    @InjectView(R.id.lv_product)
    ListView lvProduct;
    @InjectView(R.id.activity_settlement_center)
    LinearLayout activitySettlementCenter;
    @InjectView(R.id.payment_user_text)
    TextView paymentUserText;
    @InjectView(R.id.payment_addressDetail_text)
    TextView paymentAddressDetailText;
    @InjectView(R.id.rg_pay)
    RadioGroup rgPay;
    @InjectView(R.id.ll_user)
    LinearLayout llUser;
    @InjectView(R.id.tv_dy)
    TextView tvDy;


    private String paymentType = "1";
    private String timeType = "1";
    private String modeType = "1";

    private List<SettlemetCenterProductDetailResponse.ProductListBean> newList = new ArrayList<>();


    private NoScrollListView lv;

    @Override
    protected int initContentView() {
        return R.layout.activity_settlement_center;
    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);
        //初始化支付方式
        initPayRadio();
        //初始化送货时间
        initTimeRadio();
        //初始化送货方式
        initDeliverymodeRadio();
        lv = (NoScrollListView) findViewById(R.id.lv_product);
        ProductDetailAdapter adapter = new ProductDetailAdapter(newList);
        System.out.println("aaa" + newList.toString());
        lv.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        //String sku = intent.getStringExtra("sku");
        //模拟数据
        String sku = "1:3:1,2,3,4|2:2:2,3";
        String userId = App.getUserId();
        HttpParams params = new HttpParams().put("sku", sku).addHeader("userid", userId);
        App.HL.post(ConstantsRedBaby.URL_SETTLEMENTCENTER, params, SettlemetCenterProductDetailResponse.class, ConstantsRedBaby.REQUEST_SETTLEMENT_CENTER, SettlementCenterActivity.this).setTag(this);
    }

    @OnClick({R.id.rl_pay, R.id.rl_time, R.id.rl_deliverymode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_pay:
                changePay();
                break;
            case R.id.rl_time:
                changeTime();
                break;
            case R.id.rl_deliverymode:
                changeMode();
                break;
        }
    }

    //支付方式
    private int payHeight;
    private boolean payIsOpen = false;

    private void initPayRadio() {
        rgPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.pay_rb1:
                        paymentType = "1";
                        paymentPayValueText.setText("到付-现金");
                        break;
                    case R.id.pay_rb2:
                        paymentType = "2";
                        paymentPayValueText.setText("到付-Pos");
                        break;
                    case R.id.pay_rb3:
                        paymentType = "3";
                        paymentPayValueText.setText("支付宝");
                        break;
                }
            }
        });
        rgPay.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rgPay.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                payHeight = rgPay.getHeight();
                rgPay.setPadding(0, -payHeight, 0, 0);
            }
        });
    }

    //发货时间
    private int timeHeight;
    private boolean timeIsOpen = false;

    private void initTimeRadio() {
        rgTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.time_rb1:
                        timeType = "1";
                        paymentSendTimeValueText.setText("周一至周五送货");
                        break;
                    case R.id.time_rb2:
                        timeType = "2";
                        paymentSendTimeValueText.setText("双休日送货");
                        break;
                    case R.id.time_rb3:
                        timeType = "3";
                        paymentSendTimeValueText.setText("不限时间均可送货");
                        break;
                }
            }
        });
        rgTime.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rgTime.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                timeHeight = rgTime.getHeight();
                rgTime.setPadding(0, -timeHeight, 0, 0);
            }
        });
    }

    //送货方式
    private int modeHeight;
    private boolean modeIsOpen = false;

    private void initDeliverymodeRadio() {
        rgMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mode_rb1:
                        modeType = "1";
                        tvDy.setText("顺丰快递");
                        break;
                    case R.id.mode_rb2:
                        modeType = "2";
                        tvDy.setText("圆通快递");
                        break;
                    case R.id.mode_rb3:
                        modeType = "3";
                        tvDy.setText("中国邮政");
                        break;
                }
            }
        });
        rgMode.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rgMode.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                modeHeight = rgMode.getHeight();
                rgMode.setPadding(0, -modeHeight, 0, 0);
            }
        });
    }

    //送货方式的变化
    public void changeMode() {
        PaddingTopAnim anim = null;
        if (modeIsOpen) {
            anim = new PaddingTopAnim(rgMode, 0, -modeHeight);
        } else {
            anim = new PaddingTopAnim(rgMode, -modeHeight, 0);
        }
        modeIsOpen = !modeIsOpen;
        anim.start(500);
    }

    //送货时间的变化
    public void changeTime() {
        PaddingTopAnim anim = null;
        if (timeIsOpen) {
            anim = new PaddingTopAnim(rgTime, 0, -timeHeight);
        } else {
            anim = new PaddingTopAnim(rgTime, -timeHeight, 0);
        }
        timeIsOpen = !timeIsOpen;
        anim.start(500);
    }

    //支付方式的变化
    public void changePay() {
        PaddingTopAnim anim = null;
        if (payIsOpen) {
            anim = new PaddingTopAnim(rgPay, 0, -payHeight);
        } else {
            anim = new PaddingTopAnim(rgPay, -payHeight, 0);
        }
        payIsOpen = !payIsOpen;
        anim.start(500);
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    //获取数据成功和失败
    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {

        SettlemetCenterProductDetailResponse settlementResponse = (SettlemetCenterProductDetailResponse) response;
        if (settlementResponse != null && settlementResponse.getProductList() != null) {
            newList.addAll(settlementResponse.getProductList());
            setListViewHeightBasedOnChildren(lv);
        }

    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
    }
}
