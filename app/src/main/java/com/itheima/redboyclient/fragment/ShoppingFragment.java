package com.itheima.redboyclient.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.ShoppingCartListAdapter;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.greenrobot.eventbus.EventBus;
import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends MainBaseFragment implements HttpLoader.HttpListener {
    private static final String TAG = "ShoppingFragment";
    @InjectView(R.id.lv_cart)
    ListView lvCart;
    @InjectView(R.id.cb_select_all)
    CheckBox cbSelectAll;
    @InjectView(R.id.tv_buy)
    TextView tvBuy;
    @InjectView(R.id.tv_total)
    TextView tvTotal;
    @InjectView(R.id.tv_point)
    TextView tvPoint;
    @InjectView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
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
        promotionRoot = View.inflate(mActivity, R.layout.item_cart_promotion,null);
        tvPromoation = (TextView) promotionRoot.findViewById(R.id.tv_promoation);
        //初始化购物车listview
        cartList = new ArrayList<>();
        adapter = new ShoppingCartListAdapter(cartList);
        lvCart.setAdapter(adapter);
        lvCart.setDivider(null);

    }

    @Override
    protected void initData() {
        //判断登陆
//        String userId = App.getUserId();
//        if("".equals(userId)){
//            getActivity().startActivity(new Intent(getActivity(),LoginActivity.class));
//        }

        requestDataFromNet();
    }

    private void requestDataFromNet() {
        //TODO 外面包裹一个layout 访问网络切换状态
        //TODO 从数据库获取购物车条目
        String sku = "1:3:1|1:3:2|2:2:2|3:2:2";
        //从网络上获取购物车数据
        HttpParams params = new HttpParams().put("sku", sku);
        App.HL.post(ConstantsRedBaby.URL_SHOPPING_CAR, params, ShoppingCarResponse.class, ConstantsRedBaby.REQUEST_CODE_SHOPPING_CAR, this);
    }


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
                //显示促销信息
                handlePromotionData(shoppingCar);
                //显示结算信息
                handleAccountData(shoppingCar);

            } else {
                //请求的是空数据 显示空购物车
            }

        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    private void handleShoppingCarData(List<ShoppingCarResponse.CartBean> cart) {
//        rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        //rv.setAdapter(adapter);
        cartList.clear();
        cartList.addAll(cart);


    }

    private void handlePromotionData(ShoppingCarResponse shoppingCar) {
        List<String> prom = shoppingCar.getProm();
        if (prom != null && prom.size() > 0) {
            //有优惠信息显示优惠信息
            lvCart.addFooterView(promotionRoot);
            StringBuilder promotion = new StringBuilder();
            for (String str : prom) {
                promotion.append(str).append("、");
            }
            promotion.deleteCharAt(promotion.length() - 1);
            tvPromoation.setText(promotion);
        } else {
            //没有优惠信息隐藏此条目
            lvCart.removeFooterView(promotionRoot);
        }
    }

    private void handleAccountData(ShoppingCarResponse shoppingCar) {
        //设置底边结算的信息
//        int totalPrice = shoppingCar.getTotalPrice();
//        int totalPoint = shoppingCar.getTotalPoint();
//        int totalCount = shoppingCar.getTotalCount();
//        tvTotal.setText("￥ " + totalPrice);
//        tvPoint.setText("赠送积分:" + totalPoint);
    }


    @Override
    public void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }


}
