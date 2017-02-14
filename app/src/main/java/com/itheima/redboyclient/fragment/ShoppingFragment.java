package com.itheima.redboyclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.ShoppingCartListAdapter;
import com.itheima.redboyclient.db.dao.ShoppingDBDao;
import com.itheima.redboyclient.domain.Goods;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.utils.StringUtils;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends MainBaseFragment implements ShoppingCartListAdapter.OnStatusChangeListener, View.OnClickListener {
    private static final String TAG = "ShoppingFragment";
    @InjectView(R.id.lv_cart)
    ListView lvCart;
    @InjectView(R.id.rb_select_all)
    RadioButton rbSelectAll;
    @InjectView(R.id.tv_buy)
    TextView tvBuy;
    @InjectView(R.id.tv_total)
    TextView tvTotal;
    @InjectView(R.id.tv_point)
    TextView tvPoint;
    @InjectView(R.id.rl_root)
    RelativeLayout rlRoot;
    @InjectView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @InjectView(R.id.pb_shopping)
    ProgressBar pbShopping;
    @InjectView(R.id.lsl_cart)
    LoadStateLayout lslCart;
    private TextView tvPromoation;
    private View promotionRoot;
    private List<ShoppingCarResponse.CartBean> cartList;
    private ShoppingCartListAdapter adapter;

    @Override
    protected int getRootViewId() {
        return R.layout.fragment_shopping;
    }

    @Override
    protected void initView() {
        promotionRoot = View.inflate(mActivity, R.layout.item_cart_promotion, null);
        tvPromoation = (TextView) promotionRoot.findViewById(R.id.tv_promoation);
        //初始化购物车listview
        cartList = new ArrayList<>();
        adapter = new ShoppingCartListAdapter(cartList);
        lvCart.setAdapter(adapter);
        lvCart.setDivider(null);
        //增加脚布局优惠信息
        lvCart.addFooterView(promotionRoot);

        lslCart.setEmptyView(R.layout.shopping_empty);
        lslCart.setErrorView(R.layout.state_error);
        lslCart.setLoadingView(R.layout.state_loading);
        lslCart.setState(LoadStateLayout.STATE_LOADING);

    }


    @Override
    protected void initListener() {
        adapter.setOnStatusChangeListener(this);
        tvBuy.setOnClickListener(this);
        rbSelectAll.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        //从网络上获取数据填充页面数据
        requestDataFromNet();
    }




    private void initAccount() {
        rbSelectAll.setChecked(false);
        tvTotal.setText("￥ " + 0);
        tvPoint.setText("赠送积分:" + 0);
        tvBuy.setText("结算(0)");
        tvBuy.setBackgroundColor(mActivity.getResources().getColor(R.color.gray));
        tvBuy.setEnabled(false);
    }

    private void requestDataFromNet() {
        //将状态设置为加载中
        lslCart.setState(LoadStateLayout.STATE_LOADING);
        // 从数据库获取购物车条目
        List<Goods> all = new ShoppingDBDao(App.application).findAll();
        String sku = StringUtils.getSku(all);
        //String sku = null;
        if (TextUtils.isEmpty(sku)) {
            //空购物车
            lslCart.setState(LoadStateLayout.STATE_EMPTY);
            return;
        }
        //从网络上获取购物车数据
        HttpParams params = new HttpParams().put("sku", sku);
        App.HL.post(ConstantsRedBaby.URL_SHOPPING_CAR, params, ShoppingCarResponse.class, ConstantsRedBaby.REQUEST_CODE_SHOPPING_CAR, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {

                if (requestCode == ConstantsRedBaby.REQUEST_CODE_SHOPPING_CAR) {
                    //请求成功处理数据
                    ShoppingCarResponse shoppingCar = (ShoppingCarResponse) response;
                    List<ShoppingCarResponse.CartBean> cart = shoppingCar.getCart();
                    //说明请求的不是空数据
                    if (cart != null && cart.size() > 0) {
                        //数据传给listview 显示购物车信息
                        handleShoppingCarData(cart);
                        lslCart.setState(LoadStateLayout.STATE_SUCCESS);

                    } else {

                        lslCart.setState(LoadStateLayout.STATE_EMPTY);

                    }

                }
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

                MyToast.show(App.application, "数据请求失败！");

                    lslCart.setState(LoadStateLayout.STATE_ERROR);


            }
        }).setTag(this);
    }


    private void handleShoppingCarData(List<ShoppingCarResponse.CartBean> cart) {
        //刷新界面数据
        cartList.clear();
        cartList.addAll(cart);
        adapter.notifyDataSetChanged();


    }

    private void handlePromotionData(ShoppingCarResponse shoppingCar) {
        List<String> prom = shoppingCar.getProm();
        if (prom != null && prom.size() > 0) {
            //更新优惠信息
            StringBuilder promotion = new StringBuilder();
            for (String str : prom) {
                promotion.append(str).append("、");
            }
            promotion.deleteCharAt(promotion.length() - 1);
            tvPromoation.setText(promotion);
        } else {
            tvPromoation.setText("无");
        }
    }

    private void handleAccountData(ShoppingCarResponse shoppingCar) {
//        设置底边结算的信息
        int totalCount = shoppingCar.getTotalCount();
        if (totalCount == 0) {
            initAccount();
            return;
        }
        int totalPrice = shoppingCar.getTotalPrice();
        int totalPoint = shoppingCar.getTotalPoint();
        tvTotal.setText("￥ " + totalPrice);
        tvPoint.setText("赠送积分:" + totalPoint);
        tvBuy.setText("结算(" + totalCount + ")");
        tvBuy.setBackgroundColor(mActivity.getResources().getColor(R.color.darkred));
        tvBuy.setEnabled(true);

    }


    @Override
    public void emptyCart() {
        //切换到空购物车界面
        lslCart.setState(LoadStateLayout.STATE_EMPTY);
//        initAccount();
    }

    @Override
    public void onSelectedChange(List<Goods> selectedGoodsList, boolean isAllSelected) {
        rbSelectAll.setChecked(isAllSelected);
        if (selectedGoodsList.size() == 0) {
            //如果没有选中商品 初始化底边栏信息
            initAccount();
            //将促销信息设置为无
            tvPromoation.setText("无");
            return;
        }
        //获取sku属性
        String sku = StringUtils.getSku(selectedGoodsList);
        //从网络上获取数据
        HttpParams params = new HttpParams().put("sku", sku);

        pbShopping.setVisibility(View.VISIBLE);
        App.HL.post(ConstantsRedBaby.URL_SHOPPING_CAR, params, ShoppingCarResponse.class, ConstantsRedBaby.REQUEST_CODE_SHOPPING_CAR, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {
                //隐藏加载控件
                pbShopping.setVisibility(View.GONE);
                //请求成功处理数据
                ShoppingCarResponse shoppingCar = (ShoppingCarResponse) response;
                List<ShoppingCarResponse.CartBean> cart = shoppingCar.getCart();
                //说明请求的不是空数据
                if (cart != null && cart.size() > 0) {
                    //显示促销信息
                    handlePromotionData(shoppingCar);
                    //显示结算信息
                    handleAccountData(shoppingCar);

                } else {

                }


            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                //隐藏加载控件
                pbShopping.setVisibility(View.GONE);
                MyToast.show(App.application, error.getMessage());
            }
        });

    }


    @Override
    public void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:
                MyToast.show(App.application, "结算");
                break;
            case R.id.rb_select_all:
                selectAllProduct();
                break;
        }
    }

    private void selectAllProduct() {
        //radiobutton在调用onclick之前会默认将checked 设置为true 不能用来记录状态
        //所以使用selected记录radiobutton状态
        boolean selected = rbSelectAll.isSelected();
        selected = !selected;
        rbSelectAll.setSelected(selected);
        rbSelectAll.setChecked(selected);
        if (selected) {
            //全选
            adapter.selectAll();
        } else {
            //全不选
            adapter.selectEmpty();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
