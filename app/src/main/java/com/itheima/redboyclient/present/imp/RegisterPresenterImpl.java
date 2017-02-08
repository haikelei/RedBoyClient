package com.itheima.redboyclient.present.imp;


import com.itheima.redboyclient.App;
import com.itheima.redboyclient.activities.RegisterActivity;
import com.itheima.redboyclient.activities.RegisterView;
import com.itheima.redboyclient.net.resp.RegisterResponse;
import com.itheima.redboyclient.present.RegisterPresenter;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpParams;

import static com.itheima.redboyclient.utils.ConstantsRedBaby.REQUEST_CODE_REGIST;


/**
 * Created by sjk on 2016/12/26.
 */
public class RegisterPresenterImpl implements RegisterPresenter {
   private RegisterView mRegisterView;
    private RegisterActivity registerActivity;
    public RegisterPresenterImpl(RegisterView registerView) {
        mRegisterView = registerView;
        registerActivity = (RegisterActivity) registerView;
    }

    //真正的注册逻辑
    @Override
    public void regist(String username1, String pwd1) {
        HttpParams params = new HttpParams().put("username", username1).put("password", pwd1);
        App.HL.post(ConstantsRedBaby.URL_REGIST,params,RegisterResponse.class,REQUEST_CODE_REGIST,registerActivity);

    }

}
