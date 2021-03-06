package com.itheima.redboyclient.activities;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.MyOrderResponse;
import com.itheima.redboyclient.present.CancelOrder;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import butterknife.InjectView;

/**
 * Created by yudenghao on 2017/2/12.
 */

public class MyOrderActivity extends BaseActivity implements HttpLoader.HttpListener {
    @InjectView(R.id.my_order_tittle)
    TextView myOrderTittle;
    @InjectView(R.id.toolbar)
    Toolbar orderToolbar;
    @InjectView(R.id.search)
    TextView search;
    @InjectView(R.id.wuliu)
    RelativeLayout wuliu;
    @InjectView(R.id.orderId)
    TextView mOrderId;
    @InjectView(R.id.address)
    TextView address;
    @InjectView(R.id.orderTest)
    TextView orderTest;
    @InjectView(R.id.orderName)
    TextView orderName;
    @InjectView(R.id.orderAddressArea)
    TextView orderAddressArea;
    @InjectView(R.id.orderAddressDetail)
    TextView orderAddressDetail;
    @InjectView(R.id.statue)
    TextView statue;
    @InjectView(R.id.goods)
    TextView goods;
    @InjectView(R.id.pay)
    TextView pay;
    @InjectView(R.id.time)
    TextView time;
    @InjectView(R.id.goods_time)
    TextView goodsTime;
    @InjectView(R.id.ticket)
    TextView ticket;
    @InjectView(R.id.Header)
    TextView Header;
    @InjectView(R.id.advice)
    TextView advice;
    @InjectView(R.id.goods_name)
    TextView goodsName;
    @InjectView(R.id.color)
    TextView color;
    @InjectView(R.id.size)
    TextView size;
    @InjectView(R.id.line1)
    LinearLayout line1;
    @InjectView(R.id.count)
    TextView count;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.goods_count)
    TextView goodsCount;
    @InjectView(R.id.money)
    TextView money;
    @InjectView(R.id.price1)
    TextView price1;
    @InjectView(R.id.goods_money)
    TextView goodsMoney;
    @InjectView(R.id.goods_should)
    TextView goodsShould;
    @InjectView(R.id.cancel_order)
    Button cancelOrder;
    @InjectView(R.id.normal_order)
    LinearLayout normalOrder;
    @InjectView(R.id.no_order)
    RelativeLayout noOrder;
    String userId;
    MyOrderResponse myOrderResponse;
    String orderId;  //订单号
    String name;  //收件人姓名
    String addressName_Area;
    String addressDetail;
    String states;
    String times;
    @Override
    protected int initContentView() {
        return R.layout.order_activity;
    }

    @Override
    protected void initView() {
        super.initView();
        initToolBar();
    }

    private void initToolBar() {
        //默认ToolBar的标题在左侧，我们不需要
        orderToolbar.setTitle("");
        setSupportActionBar(orderToolbar);
        myOrderTittle.setText("我的订单");
        //显示左边的Home按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void initListener() {
        super.initListener();
        cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancelOrder order = new CancelOrder();
                order.cancle();
                order.setmFinish(new CancelOrder.Finish() {
                    @Override
                    public void isFinish() {
                        finish();
                    }
                });
            }
        });
        orderToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        ordersumbit();
        String orderCount = App.SP.getString("orderCount",null);
        if (orderCount.equals("0")) {
            noOrder.setVisibility(View.VISIBLE);
        } else {
            normalOrder.setVisibility(View.VISIBLE);
        }
    }

    private void ordersumbit() {
        HttpParams params = new HttpParams();
        userId = App.SP.getString("userid",null);
        params.addHeader("userid",userId);
        // params.put("sku","1:3:1,2,3,4|2:2:2,3").put("addressId","139").put("paymentType","1").put("deliveryType","1").put("invoiceType","1").put("invoiceTitle","传智慧播客教育科技有限公司").put("invoiceContent","1");
        //   App.HL.get(ConstantsRedBaby.URL_ORDERSUMBIT, params, MyOrderResponse.class, ConstantsRedBaby. REQUEST_CODE_ORDERSUMBIT ,this).setTag(this);
        params.put("orderId","098593");
        App.HL.post(ConstantsRedBaby.URL_ORDERDETAIL, params, MyOrderResponse.class, ConstantsRedBaby. REQUEST_CODE_ORDERDETAIL ,this).setTag(this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleMyOrderResopnse((MyOrderResponse) response);
    }

    private void handleMyOrderResopnse(MyOrderResponse response) {
        myOrderResponse = response;
        if (myOrderResponse != null) {
            if ( myOrderResponse.orderInfo.orderId != null) {
                orderId = myOrderResponse.orderInfo.orderId;//订单号
                mOrderId.setText("订单号:     " + orderId);

            }
            if (myOrderResponse.addressInfo != null) {
                name = myOrderResponse.addressInfo.name;  //收件人姓名
                orderTest.setText(name);
                orderName.setText("13878973788");
                addressName_Area = myOrderResponse.addressInfo.addressArea;
                orderAddressArea.setText(addressName_Area);
                addressDetail = myOrderResponse.addressInfo.addressDetail;
                orderAddressDetail.setText(addressDetail);
            }

            if (myOrderResponse.orderInfo != null) {
                states = myOrderResponse.orderInfo.status;
                statue.setText("订单状态 :      " + states);
                goods.setText("送货方式 :      " + "快递");
                pay.setText("支付方式 :      " + "支付宝");
                times = myOrderResponse.orderInfo.time;
                time.setText("订单生成时间 :      " + times);
                goodsTime.setText("发货时间 :      " + times);
                ticket.setText("是否开具发票 :      " + "否");
            }

            for (MyOrderResponse.ProductListBean productListBean : myOrderResponse.productList) {
            };
        }
    }


    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}