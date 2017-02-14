package com.itheima.redboyclient.present;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.net.resp.CancelOrderResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by yudenghao on 2017/2/13.
 */

public class CancelOrder implements HttpLoader.HttpListener {
        CancelOrderResponse mCancelOrderResponse;
        String userId;
    public void cancle() {
        userId = App.SP.getString("userid",null);
        HttpParams params = new HttpParams();
        params.put("orderId","098593");
        params.addHeader("userid",userId);
        App.HL.post(ConstantsRedBaby.URL_CANCEL, params, CancelOrderResponse.class, ConstantsRedBaby.REQUEST_CODE_CANCEL,this);
    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handlerCancel((CancelOrderResponse)response);

    }

    private void handlerCancel(CancelOrderResponse response) {
        mCancelOrderResponse = response;
        if (mCancelOrderResponse.response.equals("orderCancel")) {
            finish.isFinish();
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {

    }

    public static void setmFinish(Finish mFinish) {
        finish = mFinish;
    }

   public static Finish  finish;



    public interface Finish {
        void isFinish();
    }
}
