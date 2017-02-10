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
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.GoodDetailVPAdapter;
import com.itheima.redboyclient.net.resp.CommentResponse;
import com.itheima.redboyclient.net.resp.GoodResponse;
import com.itheima.redboyclient.present.GoodPresenter;
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

    public static String pId;
    private static GoodPresenter goodPresenter;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_market_price)
    TextView tvMarketPrice;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.textPutIntoShopcar)
    TextView textPutIntoShopcar;
    @InjectView(R.id.textProdToCollect)
    TextView textProdToCollect;
    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;
    @InjectView(R.id.spinner_corlor)
    AppCompatSpinner spinnerCorlor;
    @InjectView(R.id.spinner_size)
    AppCompatSpinner spinnerSize;
    @InjectView(R.id.bt_plus)
    Button btPlus;
    @InjectView(R.id.et_num)
    EditText etNum;
    @InjectView(R.id.bt_minus)
    Button btMinus;
    @InjectView(R.id.tv_area)
    TextView tvArea;
    @InjectView(R.id.tv_comment_count)
    TextView tvCommentCount;
    @InjectView(R.id.detail_container)
    LinearLayout detailContainer;

    private String temppid = "1";

    public GoodsDetailFragment() {
        // Required empty public constructor
        //
    }

    private static GoodsDetailFragment fragment = null;


    public static GoodsDetailFragment newInstance(String id) {
        pId = id;

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
        //测试时pid用1，以后改成pid
        HttpParams params = new HttpParams().put("pId", temppid);
        App.HL.get(ConstantsRedBaby.URL_GOODDETAIL, params, GoodResponse.class, ConstantsRedBaby.REQUEST_CODE_GOODDETAIL, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {
                GoodResponse goodResponse = (GoodResponse) response;
                initData(goodResponse);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });

    }

    private void initData(GoodResponse goodResponse) {
        GoodDetailVPAdapter adapter = new GoodDetailVPAdapter(goodResponse, getActivity());
        vp.setAdapter(adapter);
        //标题价格信息
        tvTitle.setText(goodResponse.getProduct().getName());
        tvMarketPrice.setText("市场价：¥ " + goodResponse.getProduct().getMarketPrice());
        tvPrice.setText("会员价：¥ " + goodResponse.getProduct().getPrice());
        ratingBar.setRating(goodResponse.getProduct().getScore());

        ArrayList<GoodResponse.ProductBean.ProductPropertyBean> list = (ArrayList<GoodResponse.ProductBean.ProductPropertyBean>) goodResponse.getProduct().getProductProperty();
        ArrayList<String> corlors = new ArrayList<>();
        ArrayList<String> sizes = new ArrayList<>();
        for (GoodResponse.ProductBean.ProductPropertyBean bean : list) {
            if (bean.getK().equals("颜色")) {
                corlors.add(bean.getV());
            } else if (bean.getK().equals("尺码")) {
                sizes.add(bean.getV());
            }

        }
        // 颜色尺码的信息
        ArrayAdapter corlorsAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, corlors);
        spinnerCorlor.setAdapter(corlorsAdapter);
        ArrayAdapter sizesAdapter = new ArrayAdapter(getContext(), R.layout.spinner_item, sizes);
        spinnerSize.setAdapter(sizesAdapter);
        //区域信息
        String area = goodResponse.getProduct().getInventoryArea();
        tvArea.setText("查看库存：" + area + "仓（有货）");
        //评论信息 用临时pid
        HttpParams params = new HttpParams();
        params.put("pId", temppid).put("page", "1").put("pageNum", "10");
        App.HL.get(ConstantsRedBaby.URL_COMMENT, params, CommentResponse.class, ConstantsRedBaby.REQUEST_CODE_COMMENT, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {
                CommentResponse comment = (CommentResponse) response;
                int count = comment.getComment().size();
                tvCommentCount.setText("用户评论：共有" + count + "人评论");
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
        //图片描述
        ArrayList<String> pics = (ArrayList<String>) goodResponse.getProduct().getPics();
        for (String s:pics) {
            String url = ConstantsRedBaby.URL_SERVER + s;
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            App.HL.display(iv,url);
            detailContainer.addView(iv);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    @OnClick({R.id.bt_minus, R.id.bt_plus})
    public void onClick(View view) {
        String s = etNum.getText().toString();
        int i = Integer.parseInt(s);
        switch (view.getId()) {
            case R.id.bt_minus:
                if (i <= 1) {
                    return;
                }
                etNum.setText(--i + "");
                break;
            case R.id.bt_plus:
                etNum.setText(++i + "");
                break;
        }
    }
}
