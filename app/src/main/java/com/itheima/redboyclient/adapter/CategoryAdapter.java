package com.itheima.redboyclient.adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.CategoryResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

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
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryHolder>{

    private static final String TAG = "CategoryAdapter";
    private List<CategoryResponse.CategoryBean> categoryBeanList;

    public CategoryAdapter(List<CategoryResponse.CategoryBean> categoryBeanList) {
        this.categoryBeanList = categoryBeanList;
        Log.i(TAG,"" + categoryBeanList.size());
    }

    @Override
    public CategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        CategoryHolder categoryHolder = new CategoryHolder(view);
        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(CategoryHolder holder, final int position) {
        App.HL.display(holder.imgIcon, ConstantsRedBaby.URL_SERVER + categoryBeanList.get(position).getPic());
        holder.textContent.setText(categoryBeanList.get(position).getName());
        holder.textContent.setTextColor(Color.RED);
        holder.item_describe.setText(categoryBeanList.get(position).getTag());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryBeanList.size();
    }

    public class CategoryHolder extends RecyclerView.ViewHolder {
        TextView textContent;
        ImageView imgIcon;
        TextView item_describe;
        public CategoryHolder(View itemView) {
            super(itemView);
            textContent = (TextView) itemView.findViewById(R.id.textContent);
            item_describe = (TextView) itemView.findViewById(R.id.item_describe);
            imgIcon = (ImageView) itemView.findViewById(R.id.imgIcon);
        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
