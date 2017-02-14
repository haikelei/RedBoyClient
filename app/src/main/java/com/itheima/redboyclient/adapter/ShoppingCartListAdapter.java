package com.itheima.redboyclient.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.itheima.redboyclient.Holder.ShoppingCarHolder;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.domain.Goods;
import com.itheima.redboyclient.net.resp.ShoppingCarResponse;
import com.itheima.redboyclient.utils.StringUtils;

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
        if (list.size() == 0 && listener != null) {
            //如果购物车条目为空，则通知主界面更换到空购物车界面
            listener.emptyCart();

        }
        if (cart.isSelected()) {
            //如果商品是选中状态，通知界面更新结算数据
            onSelectedChange();
        }
        notifyDataSetChanged();
    }

    @Override
    public void onSelectedChange() {
        //重新获取所有被点击条目的集合
        boolean isAllSelected = refreshSelectedGoodsList();
        //通知主界面更新
        if (listener != null) {
            listener.onSelectedChange(selectedGoodsList, isAllSelected);
        }
    }

    private boolean refreshSelectedGoodsList() {
        selectedGoodsList.clear();
        boolean isAllSelected = true;
        //从列表中根据商品的是否选中来添加商品
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ShoppingCarResponse.CartBean cartBean = list.get(i);
            if (cartBean.isSelected()) {
                //如果是选中的直接添加商品
                Goods goods = StringUtils.getGoods(cartBean);


                selectedGoodsList.add(goods);
            } else {
                //如果是未选中的状态，查看库存是否为0
                if (!"0".equals(cartBean.getProduct().getNumber())) {
                    //库存不为0 则不是全选状态
                    isAllSelected = false;
                }
            }
        }
        return isAllSelected;
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
        void emptyCart();

        void onSelectedChange(List<Goods> selectedGoodsList, boolean isAllSelected);
    }
}
