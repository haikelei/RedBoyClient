package com.itheima.redboyclient.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.GoodDetailVPAdapter;
import com.itheima.redboyclient.db.GoodSqliteOpenHelper;
import com.itheima.redboyclient.db.dao.ShoppingDBDao;
import com.itheima.redboyclient.domain.Goods;
import com.itheima.redboyclient.net.resp.FavResponse;
import com.itheima.redboyclient.net.resp.GoodResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoodsDetailFragment extends Fragment {


    private static final String TAG = "GoodsDetailFragment";
    @InjectView(R.id.vp)
    ViewPager vp;
    @InjectView(R.id.pageOne)
    NestedScrollView pageOne;

    public static GoodResponse goodResponse;

    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_market_price)
    TextView tvMarketPrice;
    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.spinner_corlor)
    AppCompatSpinner spinnerCorlor;
    @InjectView(R.id.spinner_size)
    AppCompatSpinner spinnerSize;

    @InjectView(R.id.et_num)
    EditText etNum;

    @InjectView(R.id.tv_area)
    TextView tvArea;
    @InjectView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @InjectView(R.id.detail_container)
    LinearLayout detailContainer;
    @InjectView(R.id.bt_minus)
    TextView btMinus;
    @InjectView(R.id.bt_plus)
    TextView btPlus;
    @InjectView(R.id.textPutIntoShopcar)
    TextView textPutIntoShopcar;
    @InjectView(R.id.textfavorite)
    TextView textfavorite;
    private ArrayList<GoodResponse.ProductBean.ProductPropertyBean> beans;

    public GoodsDetailFragment() {
        // Required empty public constructor
        //
    }

    private static GoodsDetailFragment fragment = null;


    public static GoodsDetailFragment newInstance(GoodResponse response) {
        goodResponse = response;
        if (fragment == null) {
            fragment = new GoodsDetailFragment();
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goods_detail_with_webview, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        GoodDetailVPAdapter adapter = new GoodDetailVPAdapter(goodResponse, getActivity());
        vp.setAdapter(adapter);

        //标题等设置
        tvTitle.setText(goodResponse.getProduct().getName());
        tvMarketPrice.setText("市场价：¥ " + goodResponse.getProduct().getMarketPrice() + "");
        tvPrice.setText("会员价：¥ " + goodResponse.getProduct().getPrice() + "");
        ratingBar.setRating(goodResponse.getProduct().getScore());
        tvArea.setText("查看库存：" + goodResponse.getProduct().getInventoryArea() + "(有货)");
        tvCommentCount.setText("用户评论：共有" + goodResponse.getProduct().getCommentCount() + "人评论");
        //下拉框设置
        ArrayList<String> corlors = new ArrayList<>();
        ArrayList<String> sizes = new ArrayList<>();
        beans = (ArrayList<GoodResponse.ProductBean.ProductPropertyBean>) goodResponse.getProduct().getProductProperty();
        for (GoodResponse.ProductBean.ProductPropertyBean bean : beans) {
            if ("颜色".equals(bean.getK())) {
                corlors.add(bean.getV());
            } else if ("尺码".equals(bean.getK())) {
                sizes.add(bean.getV());
            }
        }
        ArrayAdapter corlorArrayAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, corlors);
        spinnerCorlor.setAdapter(corlorArrayAdapter);
        ArrayAdapter sizeArrayAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, sizes);
        spinnerSize.setAdapter(sizeArrayAdapter);
        //收藏点击事件

        //加入购物车点击事件
        //商品详情图片
        ArrayList<String> list = (ArrayList<String>) goodResponse.getProduct().getPics();
        for (String s : list) {
            String url = ConstantsRedBaby.URL_SERVER + s;
            ImageView imageView = new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            App.HL.display(imageView, url);
            detailContainer.addView(imageView);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.bt_minus, R.id.bt_plus, R.id.textPutIntoShopcar, R.id.textfavorite})
    public void onClick(View view) {
        String s = etNum.getText().toString();
        int count = Integer.parseInt(s);
        switch (view.getId()) {
            case R.id.bt_minus:
                if (count <= 1) {
                    return;
                }
                etNum.setText(--count + "");
                break;
            case R.id.bt_plus:
                etNum.setText(++count + "");
                break;
            case R.id.textfavorite:
                //添加到收藏
                HttpParams params = new HttpParams().put("pId", goodResponse.getProduct().getId() + "");
                boolean islogin = App.SP.getBoolean("islogin", false);
                if (islogin) {
                    String userId = App.SP.getString("userid", null);
                    params.addHeader("userid", userId);
                } else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                }

                App.HL.get(ConstantsRedBaby.URL_FAV, params, FavResponse.class, ConstantsRedBaby.REQUEST_CODE_FAV, new HttpLoader.HttpListener() {
                    @Override
                    public void onGetResponseSuccess(int requestCode, IResponse response) {
                        Toast.makeText(getActivity(), "添加收藏夹成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {

                    }
                });
                break;
            case R.id.textPutIntoShopcar:
                //添加到购物车
                ShoppingDBDao dao = new ShoppingDBDao(getActivity());
                String corlor = spinnerCorlor.getSelectedItem().toString();
                String size = spinnerSize.getSelectedItem().toString();
                StringBuilder sb = new StringBuilder();
                for (GoodResponse.ProductBean.ProductPropertyBean bean : beans) {
                    if (corlor.equals(bean.getV())) {
                        sb.append(bean.getId());
                    }
                    if (size.equals(bean.getV())) {
                        sb.append("," + bean.getId());
                    }
                    Goods goods = new Goods(goodResponse.getProduct().getId(),
                            sb.toString(), Integer.parseInt(etNum.getText().toString()));
                    boolean suc = dao.add(goods);
                    if(suc){
                        Toast.makeText(getActivity(), "添加购物车成功", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "添加购物车失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
        }
    }
}
