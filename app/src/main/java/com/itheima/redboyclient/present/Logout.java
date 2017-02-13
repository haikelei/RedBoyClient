package com.itheima.redboyclient.present;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.net.resp.LogoutResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;
import com.itheima.redboyclient.utils.ToastUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

/**
 * Created by yudenghao on 2017/2/12.
 */

public class Logout implements HttpLoader.HttpListener {
    String userId;
    boolean islogin;
    LogoutResponse mLogoutResopnse;
    public  void logout() {
        islogin = App.SP.getBoolean("islogin", false);
        if (islogin) {
            userId = App.SP.getString("userid",null);
        } else {
            return;
        }
        HttpParams params = new HttpParams();
        params.addHeader("userid",userId);
        App.HL.get(ConstantsRedBaby.URL_LOGOUT, params, LogoutResponse.class, ConstantsRedBaby.REQUEST_CODE_LOGOUT,this).setTag(this);

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        if (!islogin) {
            return;
        }
        handleLogoutResopnse((LogoutResponse)response);
    }

    private void handleLogoutResopnse(LogoutResponse response) {
        mLogoutResopnse = response;
        if (mLogoutResopnse.response.equals("logout")) {
            App.EDIT.putBoolean("islogout",true).commit();
            ToastUtil.showToast("退出登录成功!");
            App.setUserId("");
        } else {
            ToastUtil.showToast("退出登录失败,请检查网络!");
        }
    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        ToastUtil.showToast("退出登录失败" + error);
    }
}
