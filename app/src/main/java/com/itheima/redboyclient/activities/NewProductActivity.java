package com.itheima.redboyclient.activities;

import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.NewProductAdapter;
import com.itheima.redboyclient.net.resp.NewProductResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NewProductActivity extends BaseActivity implements HttpLoader.HttpListener, AdapterView.OnItemClickListener {

    @InjectView(R.id.back)
    Button back;
    @InjectView(R.id.title)
    TextView title;
    @InjectView(R.id.text_sale)
    TextView textSale;
    @InjectView(R.id.sale)
    RelativeLayout sale;
    @InjectView(R.id.text_price)
    TextView textPrice;
    @InjectView(R.id.price)
    RelativeLayout price;
    @InjectView(R.id.text_comment)
    TextView textComment;
    @InjectView(R.id.comment)
    RelativeLayout comment;
    @InjectView(R.id.text_shelves)
    TextView textShelves;
    @InjectView(R.id.shelves)
    RelativeLayout shelves;
    @InjectView(R.id.new_product_lv)
    ListView newProductLv;
    @InjectView(R.id.fl_newproduct)
    LinearLayout flNewproduct;


    private boolean saleState = false; //是否被点击
    private boolean priceState = false; //是否被点击
    private boolean commentState = false; //是否被点击
    private boolean shelvesState = false; //是否被点击
    private String orderby = "saleDown";//默认为销量排序


    private List<NewProductResponse.ProductListBean> list = new ArrayList<>();


    private NewProductAdapter newProductAdapter;

    @Override
    protected int initContentView() {
        return R.layout.activity_newproduct;
    }

    @Override
    protected void initView() {
        ButterKnife.inject(this);
        newProductAdapter = new NewProductAdapter(list);
        newProductLv.setAdapter(newProductAdapter);
        newProductLv.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        HttpParams params = new HttpParams().put("page", "2").put("pageNum", "15").put("orderby", orderby);

        App.HL.get(ConstantsRedBaby.URL_NEWPRODUCT, params, NewProductResponse.class, ConstantsRedBaby.REQUEST_NEW_PRODUCT, NewProductActivity.this).setTag(this);
    }

    @OnClick({R.id.back, R.id.sale, R.id.price, R.id.comment, R.id.shelves})
    public void onClick(View view) {
        saleState = false;
        priceState = false;
        commentState = false;
        shelvesState = false;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.sale:
                saleState = true;
                orderby = "saleDown";
                requestAndNotifyDataChanged();

                break;
            case R.id.price:
                priceState = true;
                orderby = "priceUp";
                requestAndNotifyDataChanged();
                break;
            case R.id.comment:
                commentState = true;
                orderby = "commentDown";
                requestAndNotifyDataChanged();
                break;
            case R.id.shelves:
                shelvesState = true;
                orderby = "shelvesDown";
                requestAndNotifyDataChanged();
                break;
        }
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        NewProductResponse newProductResponse = (NewProductResponse) response;
        if (newProductResponse != null) {
            if (list.size() != 0 && list != null) {
                list.clear();
            }
            list.addAll(newProductResponse.getProductList());
            newProductAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    /**
     * 状态的改变
     */
    public void changeState() {
        if (saleState) {
            textSale.setTextColor(Color.WHITE);
            sale.setBackgroundResource(R.drawable.segment_selected_1_bg);
        } else {
            textSale.setTextColor(Color.BLACK);
            sale.setBackgroundResource(R.drawable.segment_normal_1_bg);
        }
        if (priceState) {
            textPrice.setTextColor(Color.WHITE);
            price.setBackgroundResource(R.drawable.segment_selected_2_bg);
        } else {
            textPrice.setTextColor(Color.BLACK);
            price.setBackgroundResource(R.drawable.segment_normal_2_bg);
        }
        if (commentState) {
            textComment.setTextColor(Color.WHITE);
            comment.setBackgroundResource(R.drawable.segment_selected_2_bg);
        } else {
            textComment.setTextColor(Color.BLACK);
            comment.setBackgroundResource(R.drawable.segment_normal_2_bg);
        }
        if (shelvesState) {
            textShelves.setTextColor(Color.WHITE);
            shelves.setBackgroundResource(R.drawable.segment_selected_3_bg);
        } else {
            textShelves.setTextColor(Color.BLACK);
            shelves.setBackgroundResource(R.drawable.segment_normal_3_bg);
        }
    }


    public void requestAndNotifyDataChanged() {
        changeState();
        HttpParams params = new HttpParams().put("page", "2").put("pageNum", "15").put("orderby", orderby);
        App.HL.get(ConstantsRedBaby.URL_NEWPRODUCT, params, NewProductResponse.class, ConstantsRedBaby.REQUEST_NEW_PRODUCT, this).setTag(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}