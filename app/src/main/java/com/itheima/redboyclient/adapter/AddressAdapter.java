package com.itheima.redboyclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.AddressResponse;

import java.util.List;

/**
 * ━━━━ Code is far away from ━━━━━━
 * 　　  () 　　　()
 * 　　  ( ) 　　( )
 * 　　  ( ) 　　( )
 * 　　┏━┛┻━━━━━━┛┻┓
 * 　　┃　　　━　　 ┃
 * 　　┃　┳┛　  ┗┳ ┃
 * 　　┃　　　┻　　 ┃
 * 　　┗━━┓　　　┏━┛
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┗━━┣┓
 * 　　　　┃　　　　┏━━━━┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * ━━━━ bug with the XYY protecting━━━
 */
public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder>{

    private List<AddressResponse.AddressListBean> list;
    private int clickPosition = -1;

    public AddressAdapter(List<AddressResponse.AddressListBean> list) {
        this.list = list;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_child_item,parent,false);
        AddressViewHolder holder = new AddressViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AddressViewHolder holder, final int position) {
        AddressResponse.AddressListBean bean = list.get(position);
        holder.address_listitem_ads_text.setText(bean.getProvince() + bean.getCity() + bean.getAddressArea() + bean.getAddressDetail());
        holder.address_listitem_receiver_text.setText(bean.getName());
        holder.address_listitem_phone_text.setText(bean.getPhoneNumber());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v,position);
                }
            }
        });
        if (position == clickPosition) {
            holder.address_arror_img.setVisibility(View.VISIBLE);
        }else {
            holder.address_arror_img.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void notifyDataSetChanged(List<AddressResponse.AddressListBean> list,int clickPosition) {
        this.list = list;
        this.clickPosition = clickPosition;
        notifyDataSetChanged();
    }


    public class AddressViewHolder extends RecyclerView.ViewHolder {

        TextView address_listitem_receiver_text;
        TextView address_listitem_phone_text;
        ImageView address_arror_img;
        TextView address_listitem_ads_text;

        public AddressViewHolder(View itemView) {
            super(itemView);
            address_arror_img = (ImageView) itemView.findViewById(R.id.address_arror_img);
            address_listitem_ads_text = (TextView) itemView.findViewById(R.id.address_listitem_ads_text);
            address_listitem_phone_text = (TextView) itemView.findViewById(R.id.address_listitem_phone_text);
            address_listitem_receiver_text = (TextView) itemView.findViewById(R.id.address_listitem_receiver_text);
        }
    }

    public OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }

    public void setOnItemClickListen(OnItemClickListener listener){
        this.listener = listener;
    }

}
