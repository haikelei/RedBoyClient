package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.redboyclient.Holder.ShoppingCarHolder;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.domain.Goods;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;

import org.senydevpkg.utils.ALog;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by gary on 2017/2/8.
 */

public class ShoppingCartListAdapter extends BaseAdapter implements ShoppingCarHolder.OnStatusChangeListener {
    protected List<ShoppingCarResponse.CartBean> list;
    protected List<Goods> selectedGoodsList;
    private OnStatusChangeListener listener;

    public void setOnStatusChangeListener(OnStatusChangeListener listener) {
        this.listener = listener;
    }

    public ShoppingCartListAdapter(List<ShoppingCarResponse.CartBean> list) {
        super();
        this.list = list;
        selectedGoodsList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        //TODO 使用假数据
        if (list.size() > 0) {
            ALog.e("..........................................................");
            ALog.e(list.toString());
            list.get(0).getProduct().setNumber("0");
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1. 初始化holder
        ShoppingCarHolder holder;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.shoppingcart_listitem, null);
            holder = new ShoppingCarHolder(convertView);
            holder.setOnStatusChangeListener(this);
        } else {
            holder = (ShoppingCarHolder) convertView.getTag();

        }
        //3. 绑定数据
        holder.setCart(list.get(position));
        holder.bindData();
        return convertView;
    }


    @Override
    public void onDeleteCart(ShoppingCarResponse.CartBean cart) {
        //删除条目数据，刷新界面
        list.remove(cart);
        if (cart.isSelected()) {
            //如果商品是选中状态，通知界面更新结算数据
            onSelectedChange();
        }
        notifyDataSetChanged();
        //TODO 通知购物车数量改变
    }

    @Override
    public void onSelectedChange() {
        //重新获取所有被点击条目的集合
        refreshSelectedGoodsList();
        //通知主界面更新
        if (listener != null) {
            listener.onSelectedChange(selectedGoodsList);
        }
    }

    private void refreshSelectedGoodsList() {
        selectedGoodsList.clear();
        //从列表中根据商品的是否选中来添加商品
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ShoppingCarResponse.CartBean cartBean = list.get(i);
            if (cartBean.isSelected()) {
                //获取每个商品的数量
                int prodNum = cartBean.getProdNum();
                ShoppingCarResponse.CartBean.ProductBean product = cartBean.getProduct();
                //获取商品ID
                int id = product.getId();
                //获取商品属性
                List<ShoppingCarResponse.CartBean.ProductBean.ProductPropertyBean> productProperty = product.getProductProperty();
                //拼接商品属性
                StringBuilder productPropertyId = new StringBuilder();
                for (int j = 0; ; j++) {
                    ShoppingCarResponse.CartBean.ProductBean.ProductPropertyBean productPropertyBean = productProperty.get(j);
                    int PropertyId = productPropertyBean.getId();
                    productPropertyId.append(PropertyId);
                    if (j == productProperty.size() - 1) {
                        break;
                    }
                    productPropertyId.append(",");
                }
                Goods goods = new Goods();
                goods.setProductId(id);
                goods.setProductNum(prodNum);
                goods.setProductPropertyId(productPropertyId.toString());
                selectedGoodsList.add(goods);
            }
        }
    }

    public void selectEmpty() {
        for (ShoppingCarResponse.CartBean cart : list) {
            //将所有的条目selcted属性设置为未选中
            cart.setSelected(false);
        }
        //通知主界面请求网络获取结算中心数据
        onSelectedChange();

    }

    public void selectAll() {
        for (ShoppingCarResponse.CartBean cart : list) {
            //将所有有库存的条目selcted属性设置为选中
            if (!"0".equals(cart.getProduct().getNumber())) {
                cart.setSelected(true);
            }
        }
        //通知主界面请求网络获取结算中心数据
        onSelectedChange();

    }


    public interface OnStatusChangeListener {

        void onSelectedChange(List<Goods> selectedGoodsList);
    }
}
