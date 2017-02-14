package com.itheima.redboyclient.view;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itheima.redboyclient.R;
import com.itheima.redboyclient.activities.SearchSecondActivity;

import org.senydevpkg.utils.ALog;
import org.senydevpkg.utils.MyToast;

/**
 * Created by fee1in on 2017/2/14.
 */
public class MySearchView extends ViewGroup implements View.OnClickListener {

    private TextInputEditText editSearchInfo;
    private TextView tvSearch;
    private LinearLayout llSearch;
    private FrameLayout rootView;
    private Context context;



    private OnSearchListener listener;
    public void setOnSearchListener(OnSearchListener listener) {
        this.listener = listener;
    }



    public MySearchView(Context context) {
        super(context);
        initView(context);
    }

//    public MySearchView(Context context,OnSearchListener listener) {
//        super(context);
//        initView(context);
//        this.listener = listener;
//    }


    public MySearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    public MySearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.searchview, this, true);
        rootView = (FrameLayout) view.findViewById(R.id.fl_root);
        llSearch = (LinearLayout) view.findViewById(R.id.llSearch);
        editSearchInfo = (TextInputEditText) view.findViewById(R.id.editSearchInfo);
        tvSearch = (TextView) view.findViewById(R.id.tv_search);
        tvSearch.setOnClickListener(this);

    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        rootView.measure(widthMeasureSpec, 0);
        setMeasuredDimension(rootView.getMeasuredWidth(), llSearch.getMeasuredHeight());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        rootView.layout(0, 0, rootView.getMeasuredWidth(), rootView.getMeasuredHeight());
    }

    public void setText(String searchInfo) {
        editSearchInfo.setText(searchInfo);
        editSearchInfo.setSelection(searchInfo.length());
    }


    @Override
    public void onClick(View v) {
        search();
    }

    public void search() {
        String searchInfo = editSearchInfo.getText().toString().trim();

        if (TextUtils.isEmpty(searchInfo)) {
            MyToast.show(context, "请输入搜索内容");
        } else {
            Intent intent = new Intent(context, SearchSecondActivity.class);
            intent.putExtra("keyword", searchInfo);
            context.startActivity(intent);
            ALog.e(".....................................................................");
            if (listener != null) {
                ALog.e(".....................................................................");
                listener.onSearch(searchInfo);
            }


        }
    }

    public interface OnSearchListener {
        void onSearch(String searchInfo);
    }
}
