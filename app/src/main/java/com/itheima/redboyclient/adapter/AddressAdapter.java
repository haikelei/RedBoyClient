package com.itheima.redboyclient.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.AddAddressActivity;
import com.itheima.redboyclient.activities.AddressActivity;
import com.itheima.redboyclient.net.resp.AddressResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.utils.ToastUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

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

    private static final String TAG = "AddressAdapter";
    private List<AddressResponse.AddressListBean> list;
    private int clickPosition = -1;
    private AddressActivity addressActivity;

    public AddressAdapter(AddressActivity addressActivity, List<AddressResponse.AddressListBean> list) {
        this.list = list;
        this.addressActivity = addressActivity;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.address_manage_listitem,parent,false);
        AddressViewHolder holder = new AddressViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AddressViewHolder holder, final int position) {
        final AddressResponse.AddressListBean bean = list.get(position);
        holder.address_listitem_ads_text.setText(bean.getProvince() + bean.getCity() + bean.getAddressArea() + bean.getAddressDetail());
        holder.address_listitem_receiver_text.setText(bean.getName());
        holder.address_listitem_phone_text.setText(bean.getPhoneNumber());
        holder.checkbox.setChecked(bean.getIsDefault() == 0?false:true);
        if (bean.getIsDefault() == 0) {
            holder.checkbox.setText("设置默认");
        }else {
            holder.checkbox.setText("默认地址");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v,position);
                }
            }
        });
        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpParams params = new HttpParams().addHeader("userid",App.SP.getString("userid",null));
                params.put("id",bean.getId() + "");
                App.HL.get(ConstantsRedBaby.URL_ADDRESSDEFAULT, params, AddressResponse.class, ConstantsRedBaby.REQUEST_CODE_ADDRESSDEFAULT, new HttpLoader.HttpListener() {

                    @Override
                    public void onGetResponseSuccess(int requestCode, IResponse response) {

                        ToastUtil.showToast("设置默认地址成功");
                        addressActivity.refresh();

                    }

                    @Override
                    public void onGetResponseError(int requestCode, VolleyError error) {
                        ToastUtil.showToast("设置默认地址失败");
                    }
                }).setTag(this);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setCancelable(false)
                        .setTitle("请选择")
                        .setMessage("请问是否删除地址?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(final DialogInterface dialog, int
                                    which) {
                                HttpParams params = new HttpParams().addHeader("userid",App.SP.getString("userid",null));
                                params.put("id",bean.getId() + "");
                                App.HL.get(ConstantsRedBaby.URL_ADDRESSDELETE, params, AddressResponse.class, ConstantsRedBaby.REQUEST_CODE_ADDRESSDELETE, new HttpLoader.HttpListener() {

                                    @Override
                                    public void onGetResponseSuccess(int requestCode, IResponse response) {
                                        dialog.dismiss();
                                        ToastUtil.showToast("删除成功");
                                        addressActivity.refresh();
                                    }

                                    @Override
                                    public void onGetResponseError(int requestCode, VolleyError error) {
                                        ToastUtil.showToast("删除失败");
                                    }
                                }).setTag(this);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
            }
        });
        holder.modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                new AlertDialog.Builder(v.getContext())
                        .setCancelable(false)
                        .setTitle("请选择")
                        .setMessage("请问是否修改地址?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(final DialogInterface dialog, int
                                    which) {
                                Intent intent = new Intent(addressActivity, AddAddressActivity.class);
                                intent.putExtra("id",bean.getId())
                                        .putExtra("name", bean.getName())
                                        .putExtra("phoneNumber", bean.getPhoneNumber())
                                        .putExtra("province", bean.getProvince())
                                        .putExtra("city", bean.getCity())
                                        .putExtra("addressArea", bean.getAddressArea())
                                        .putExtra("addressDetail", bean.getAddressDetail())
                                        .putExtra("zipCode", bean.getZipCode())
                                        .putExtra("isDefault", bean.getIsDefault());
                                v.getContext().startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消",null)
                        .show();
            }
        });

//        if (position == clickPosition) {
//            holder.address_arror_img.setVisibility(View.VISIBLE);
//        }else {
//            holder.address_arror_img.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        Log.i(TAG,list.size() + "");
        return list.size();
    }

    public void notifyDataSetChanged(List<AddressResponse.AddressListBean> list) {
        this.list = list;
        //this.clickPosition = clickPosition;
        notifyDataSetChanged();
    }


    public class AddressViewHolder extends RecyclerView.ViewHolder {

        TextView address_listitem_receiver_text;
        TextView address_listitem_phone_text;
        ImageView address_arror_img;
        TextView address_listitem_ads_text;
        CheckBox checkbox;
        TextView delete;
        TextView modify;

        public AddressViewHolder(View itemView) {
            super(itemView);
            address_arror_img = (ImageView) itemView.findViewById(R.id.address_arror_img);
            address_listitem_ads_text = (TextView) itemView.findViewById(R.id.address_listitem_ads_text);
            address_listitem_phone_text = (TextView) itemView.findViewById(R.id.address_listitem_phone_text);
            address_listitem_receiver_text = (TextView) itemView.findViewById(R.id.address_listitem_receiver_text);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            delete = (TextView) itemView.findViewById(R.id.delete);
            modify = (TextView) itemView.findViewById(R.id.modify);
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
