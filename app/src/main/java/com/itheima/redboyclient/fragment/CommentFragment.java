package com.itheima.redboyclient.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.adapter.CommentAdapter;
import com.itheima.redboyclient.net.resp.CommentResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment {

    private static final String TAG = "CommentFragment";
    private static String id;
    @InjectView(R.id.lv)
    ListView lv;

    public CommentFragment() {
        // Required empty public constructor
    }


    private static CommentFragment fragment = null;

    public static CommentFragment newInstance(String pId) {
        id = pId;
        if (fragment == null) {
            fragment = new CommentFragment();
        }
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        HttpParams params = new HttpParams().put("pId",id).put("page","1").put("pageNum","10");
        App.HL.get(ConstantsRedBaby.URL_COMMENT, params, CommentResponse.class, ConstantsRedBaby.REQUEST_CODE_COMMENT, new HttpLoader.HttpListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, IResponse response) {
                CommentResponse commentResponse = (CommentResponse) response;


                CommentAdapter adapter = new CommentAdapter(getActivity(),commentResponse);
                lv.setAdapter(adapter);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
