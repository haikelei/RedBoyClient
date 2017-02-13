package com.itheima.redboyclient.activities;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.DetailAdapter;
import com.itheima.redboyclient.net.resp.NewProductDetailResponse;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NewProductDetailActivity extends BaseActivity implements HttpLoader.HttpListener {
    private static final String TAG = "NewProductDetailActivit";
    @InjectView(R.id.vp_product)
    ViewPager vpProduct;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_price)
    TextView tvPrice;
    @InjectView(R.id.tv_pingfen)
    TextView tvPingfen;
    @InjectView(R.id.tv_vipprice)
    TextView tvVipprice;
    @InjectView(R.id.tv_num)
    TextView tvNum;
    @InjectView(R.id.bt_goin)
    Button btGoin;
    @InjectView(R.id.bt_collection)
    Button btCollection;
    @InjectView(R.id.tv_more)
    TextView tvMove;
    @InjectView(R.id.tv_comment)
    TextView tvComment;

   private List<NewProductDetailResponse.ProductBean.ProductPropertyBean> DetailList = new ArrayList();
    private NewProductDetailResponse.ProductBean productBean = new NewProductDetailResponse.ProductBean();
    @Override
    protected int initContentView() {
        int id = R.layout.activity_new_productdetail;
        return id;
    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);

        DetailAdapter adapter = new DetailAdapter(productBean,this);
        vpProduct.setAdapter(adapter);


        int position = getIntent().getIntExtra("position",0);
        tvName.setText(productBean.getName());
        tvComment.setText(productBean.getCommentCount()+"个");
        tvNum.setText(productBean.getBuyLimit() + "个");
        tvPingfen.setText(productBean.getScore() + "个");

    }

    @Override
    protected void initData() {

        //HttpParams params = new HttpParams().put("pId", "1");
       // App.HL.get(ConstantsRedBaby.URL_NEWPRODUCT_DETAIL, params, NewProductDetailResponse.class, ConstantsRedBaby.REQUEST_NEW_PRODUCTDETAIL, NewProductDetailActivity.this).setTag(this);

    }

    @OnClick({R.id.bt_goin, R.id.bt_collection})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_goin:
                break;
            case R.id.bt_collection:
                break;
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        NewProductDetailResponse newProductDetailResponse = (NewProductDetailResponse) response;
        if (newProductDetailResponse != null) {

            productBean = newProductDetailResponse.getProduct();

        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }
}
