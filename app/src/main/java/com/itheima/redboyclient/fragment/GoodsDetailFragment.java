package com.itheima.redboyclient.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.GoodDetailVPAdapter;
import com.itheima.redboyclient.net.resp.GoodResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;


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
    @InjectView(R.id.bt_minus)
    Button btMinus;
    @InjectView(R.id.et_num)
    EditText etNum;
    @InjectView(R.id.bt_plus)
    Button btPlus;
    @InjectView(R.id.textPutIntoShopcar)
    TextView textPutIntoShopcar;
    @InjectView(R.id.textProdToCollect)
    TextView textProdToCollect;
    @InjectView(R.id.tv_area)
    TextView tvArea;
    @InjectView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @InjectView(R.id.detail_container)
    LinearLayout detailContainer;

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
        tvMarketPrice.setText("市场价：¥ "+goodResponse.getProduct().getMarketPrice()+"");
        tvPrice.setText("会员价：¥ "+goodResponse.getProduct().getPrice()+"");
        ratingBar.setRating(goodResponse.getProduct().getScore());
        tvArea.setText("查看库存："+goodResponse.getProduct().getInventoryArea()+"(有货)");
        tvCommentCount.setText("用户评论：共有"+goodResponse.getProduct().getCommentCount()+"人评论");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
