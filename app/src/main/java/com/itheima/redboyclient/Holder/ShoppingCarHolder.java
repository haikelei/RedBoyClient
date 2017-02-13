package com.itheima.redboyclient.Holder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.utils.MyToast;

import java.util.List;

/**
 * Created by fee1in on 2017/2/11.
 */
public class ShoppingCarHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //商品图片
    ImageView ivPic;
    //正常页面商品标题
    TextView tvTitle;
    //正常页面商品详情
    TextView tvDetail;
    //正常页面商品价格
    TextView tvPrice;
    //正常页面商品个数
    TextView tvNum;
    //正常页面父控件
    LinearLayout llNormal;
    //编辑页面减少商品数量
    TextView tvReduce;
    //编辑页面商品数量
    TextView tvEditNum;
    //编辑页面增加商品数量
    TextView tvAdd;
    //编辑页面商品详情
    TextView tvEditDetail;
    //编辑页面删除商品
    TextView tvDelete;
    //编辑页面父控件
    LinearLayout llEdit;
    //库存信息
    TextView tvStock;
    //编辑按钮
    TextView tvEdit;

    //选择框
    RadioButton rbSelect;
    private ShoppingCarResponse.CartBean cart;
    private ShoppingCarResponse.CartBean.ProductBean product;
    private OnStatusChangeListener listener;
    private int colorGrayId = App.application.getResources().getColor(R.color.gray);

    public void setCart(ShoppingCarResponse.CartBean cart) {
        this.cart = cart;
        this.product = cart.getProduct();
        //单选框与此条目绑定
        cart.setOnSelectedChangeListener(new ShoppingCarResponse.CartBean.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(boolean selected) {
                rbSelect.setChecked(selected);
            }
        });
    }

    public void setOnStatusChangeListener(OnStatusChangeListener listener) {
        this.listener = listener;
    }

    public ShoppingCarHolder(View itemView) {
        super(itemView);
        initView(itemView);
        initListener();
        itemView.setTag(this);


    }


    private void initView(View itemView) {
        ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
        tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
        tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        tvNum = (TextView) itemView.findViewById(R.id.tv_num);
        llNormal = (LinearLayout) itemView.findViewById(R.id.ll_normal);
        tvReduce = (TextView) itemView.findViewById(R.id.tv_reduce);
        tvEditNum = (TextView) itemView.findViewById(R.id.tv_edit_num);
        tvAdd = (TextView) itemView.findViewById(R.id.tv_add);
        tvEditDetail = (TextView) itemView.findViewById(R.id.tv_edit_detail);
        tvDelete = (TextView) itemView.findViewById(R.id.tv_delete);
        llEdit = (LinearLayout) itemView.findViewById(R.id.ll_edit);
        tvEdit = (TextView) itemView.findViewById(R.id.tv_edit);
        tvStock = (TextView) itemView.findViewById(R.id.tv_stock);
        rbSelect = (RadioButton) itemView.findViewById(R.id.rb_select);
    }

    private void initListener() {
        tvEdit.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        tvAdd.setOnClickListener(this);
        tvReduce.setOnClickListener(this);
        rbSelect.setOnClickListener(this);
    }

    public void bindData() {
        //检查购买数量是否超过购买限定（接口文档的坑）
        checkProdNum();
        //检查无货状态
        checkStock();
        //显示商品详情
        switchEditPager();
        //填充数据
        fillData(product);


    }

    private void checkProdNum() {
        //将商品的可购买数量调整不能超过上限
        String number = product.getNumber();
        int stockNum = Integer.parseInt(number);
        int buyLimit = product.getBuyLimit();
        int prodNum = cart.getProdNum();
        prodNum = prodNum < stockNum ? prodNum : stockNum;
        prodNum = prodNum < buyLimit ? prodNum : buyLimit;
        cart.setProdNum(prodNum);
    }

    private void checkStock() {
        String number = product.getNumber();
        int stockNum = Integer.parseInt(number);
        if (stockNum == 0) {
            //字体颜色改为灰色
            tvTitle.setTextColor(colorGrayId);
            tvPrice.setTextColor(colorGrayId);
            //选择框不能被点击
            rbSelect.setEnabled(false);
            //背景改为浅灰色
            itemView.setBackgroundColor(App.application.getResources().getColor(R.color.lightlightgray));
        }else{
            //字体颜色改为灰色
            tvTitle.setTextColor(App.application.getResources().getColor(R.color.black));
            tvPrice.setTextColor(App.application.getResources().getColor(R.color.darkred));
            //选择框能被点击
            rbSelect.setEnabled(true);
            //将背景颜色去掉
            itemView.setBackgroundColor(Color.TRANSPARENT);
        }
    }


    private void fillData(ShoppingCarResponse.CartBean.ProductBean product) {
        //显示商品是否勾选
        boolean selected = cart.isSelected();
        rbSelect.setChecked(selected);
        //填充商品名称
        String name = product.getName();
        tvTitle.setText(name);

        //填充库存信息
        int buyLimit = product.getBuyLimit();
        String number = product.getNumber();
        int stockNum = Integer.parseInt(number);
        if (stockNum > buyLimit) {
            tvStock.setText("限购：" + buyLimit);
        } else {
            tvStock.setText("库存：" + stockNum);
            if (stockNum == 0) {
                tvStock.setText("宝贝已经卖光了");
            }
        }


        //填充商品详情
        List<ShoppingCarResponse.CartBean.ProductBean.ProductPropertyBean> productProperty = product.getProductProperty();
        StringBuilder property = new StringBuilder();
        for (int i = 0; i < productProperty.size(); i++) {
            //拼接商品详情数据
            ShoppingCarResponse.CartBean.ProductBean.ProductPropertyBean productPropertyBean = productProperty.get(i);
            property.append(productPropertyBean.getK()).append(":").append(productPropertyBean.getV());
            if (i != productProperty.size() - 1) {
                property.append(";");
            }
        }

        tvDetail.setText(property);
        tvEditDetail.setText(property);


        //显示图片
        String pic = product.getPic();
        App.HL.display(ivPic, ConstantsRedBaby.URL_SERVER + pic);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_edit:
                boolean editing = cart.isEditing();
                cart.setEditing(!editing);
                switchEditPager();
                break;
            case R.id.tv_delete:
                deleteProduct();
                break;
            case R.id.tv_add:
                addProduct();
                break;
            case R.id.tv_reduce:
                reduceProduct();
                break;
            case R.id.rb_select:
                selectProduct();
                break;
        }
    }

    private void selectProduct() {
        //改变选择框状态
        boolean selected = cart.isSelected();
        selected = !selected;
        cart.setSelected(selected);
        if (listener != null) {
            //通知adapter条目被选中
            listener.onSelectedChange();
        }
    }

    private void addProduct() {
        int prodNum = cart.getProdNum();
        int buyLimit = product.getBuyLimit();
        String number = product.getNumber();
        int stockNum = Integer.parseInt(number);
        if (stockNum > buyLimit && prodNum >= buyLimit) {
            //如果库存大于限购，请求的商品数量大于等于限购
            MyToast.show(App.application, "限购:" + buyLimit);
        } else if (stockNum < buyLimit && prodNum >= stockNum) {
            //如果库存小于限购，请求的商品数量大于等于库存
            MyToast.show(App.application, "库存:" + stockNum);
        } else {
            //增加商品数量
            cart.setProdNum(prodNum + 1);
            //刷新编辑页面
            switchEditPager();
        }
        //在选中状态下通知页面刷新结算数据
        if (cart.isSelected() && listener != null) {
            listener.onSelectedChange();
        }

    }

    private void reduceProduct() {

        int prodNum = cart.getProdNum();
        if (prodNum <= 1) {
            //如果当前数量小于等于1 删除商品确认
            deleteProduct();
            return;
        }
        //减少商品数量
        cart.setProdNum(prodNum - 1);
        //刷新编辑页面
        switchEditPager();
        //在选中状态下通知页面刷新结算数据
        if (cart.isSelected() && listener != null) {
            listener.onSelectedChange();
        }
    }

    private void deleteProduct() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ivPic.getContext());
        builder.setTitle("确认删除");
        builder.setMessage("是否删除选中商品?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    //通知界面条目被删除
                    listener.onDeleteCart(cart);
                }
            }
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    private void switchEditPager() {
        if (cart.isEditing()) {
            //显示编辑页面
            llNormal.setVisibility(View.GONE);
            llEdit.setVisibility(View.VISIBLE);
            tvEdit.setText("完成");
            //编辑页面显示数量
            int prodNum = cart.getProdNum();
            tvEditNum.setText(prodNum + "");
        } else {
            //显示商品详情页面
            llNormal.setVisibility(View.VISIBLE);
            llEdit.setVisibility(View.GONE);
            tvEdit.setText("编辑");
            //详情页显示数量
            int prodNum = cart.getProdNum();
            tvNum.setText("x " + prodNum);
            //详情页显示价格
            int price = product.getPrice();
            tvPrice.setText("¥ " + price);
        }

    }

//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//        cart.setSelected(isChecked);
//        if (listener != null) {
//            //通知adapter条目被选中
//            listener.onSelectedChange();
//        }
//
//    }

    public interface OnStatusChangeListener {
        void onDeleteCart(ShoppingCarResponse.CartBean cart);

        void onSelectedChange();
    }
}
