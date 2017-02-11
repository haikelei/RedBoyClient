package com.itheima.redboyclient.activities;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.TopicAdapter;
import com.itheima.redboyclient.net.resp.TopicResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;
import org.senydevpkg.utils.MyToast;
import org.senydevpkg.view.LoadStateLayout;

import butterknife.InjectView;

public class TopicActivity extends BaseActivity implements HttpLoader.HttpListener {


    @InjectView(R.id.backTv)
    TextView mBackTv;
    @InjectView(R.id.textTitle)
    TextView mTextTitle;
    @InjectView(R.id.fraHead)
    FrameLayout mFraHead;
    @InjectView(R.id.mylimitbuy_product_list)
    ListView mMylimitbuyProductList;
    @InjectView(R.id.myfavorite_productlist_layout)
    LoadStateLayout mMyfavoriteProductlistLayout;
    @InjectView(R.id.bottomSpace)
    RelativeLayout mBottomSpace;
    @InjectView(R.id.imgHome)
    ImageView mImgHome;
    @InjectView(R.id.imgClassify)
    ImageView mImgClassify;
    @InjectView(R.id.imgSearch)
    ImageView mImgSearch;
    @InjectView(R.id.imgShoppingCar)
    ImageView mImgShoppingCar;
    @InjectView(R.id.imgMore)
    ImageView mImgMore;
    @InjectView(R.id.linToolBar)
    LinearLayout mLinToolBar;
    @InjectView(R.id.textShopCarNum)
    TextView mTextShopCarNum;
    private TopicResponse topics;
    private TopicAdapter adapter;

    @Override
    protected int initContentView() {
        return R.layout.my_limit_activity;
    }

    @Override
    protected void initView() {
        mMyfavoriteProductlistLayout.setEmptyView(R.layout.state_empty);
        mMyfavoriteProductlistLayout.setErrorView(R.layout.state_error);
        mMyfavoriteProductlistLayout.setLoadingView(R.layout.state_loading);
        mMyfavoriteProductlistLayout.setState(LoadStateLayout.STATE_LOADING);
    }

    @Override
    protected void initData() {
//        PromotionFragment fm = new PromotionFragment();
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl,fm).commit();
        HttpParams params = new HttpParams().put("page", "1").put("pageNum", "8");

        App.HL.get(ConstantsRedBaby.URL_TOPIC, params, TopicResponse.class, ConstantsRedBaby.REQUEST_CODE_TOPIC, TopicActivity.this).setTag(this);

    }


    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleTopicResponse((TopicResponse) response);
    }

    /**
     * 处理Topic专题数据的展示
     *
     * @param response
     */
    private void handleTopicResponse(TopicResponse response) {
        topics = response;
        if (topics.topic != null && topics.topic.size() > 0) {
            if (adapter == null) {
                adapter = new TopicAdapter(getApplicationContext(), topics.topic);
                mMylimitbuyProductList.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged(topics.topic);
            }
            mMyfavoriteProductlistLayout.setState(LoadStateLayout.STATE_SUCCESS);//显示请求成功的View
        } else {
            mMyfavoriteProductlistLayout.setState(LoadStateLayout.STATE_EMPTY);//显示数据为空的View
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        if (adapter == null)//如果当前没有设置过数据
            mMyfavoriteProductlistLayout.setState(LoadStateLayout.STATE_ERROR);//显示请求错误的View

        MyToast.show(getApplicationContext(), "数据请求失败！");
    }

    @Override
    protected void onStop() {
        super.onStop();
        App.HL.cancelRequest(this);
    }

}
