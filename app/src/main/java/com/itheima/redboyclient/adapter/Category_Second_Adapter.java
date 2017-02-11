package com.itheima.redboyclient.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.CategoryResponse;

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
public class Category_Second_Adapter extends RecyclerView.Adapter<Category_Second_Adapter.CategorySecondHolder> {
    private static final String TAG = "CategoryAdapter";
    private List<CategoryResponse.CategoryBean> categoryBeanList;

    public Category_Second_Adapter(List<CategoryResponse.CategoryBean> categoryBeanList) {
        this.categoryBeanList = categoryBeanList;
        Log.i(TAG, "" + categoryBeanList.size());
    }

    @Override
    public CategorySecondHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_child_item, parent, false);
        CategorySecondHolder categoryHolder = new CategorySecondHolder(view);
        return categoryHolder;
    }

    @Override
    public void onBindViewHolder(CategorySecondHolder holder, final int position) {
        holder.textContent.setText(categoryBeanList.get(position).getName());
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

    public class CategorySecondHolder extends RecyclerView.ViewHolder {
        TextView textContent;
        public CategorySecondHolder(View itemView) {
            super(itemView);
            textContent = (TextView) itemView.findViewById(R.id.textContent);
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

