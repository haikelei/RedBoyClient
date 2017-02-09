package com.itheima.redboyclient.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.RegisterResponse;
import com.itheima.redboyclient.present.RegisterPresenter;
import com.itheima.redboyclient.present.imp.RegisterPresenterImpl;
import com.itheima.redboyclient.utils.StringUtils;
import com.itheima.redboyclient.utils.ToastUtil;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.resp.IResponse;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by sjk on 2017/2/7.
 */

public class RegisterActivity extends BaseActivity implements TextView.OnEditorActionListener, RegisterView,HttpLoader.HttpListener {


    @InjectView(R.id.registrt_head_back_text)
    TextView registrtHeadBackText;
    @InjectView(R.id.fraHead)
    FrameLayout fraHead;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.til_username)
    TextInputLayout tilUsername;
    @InjectView(R.id.et_pwd)
    EditText etPwd;
    @InjectView(R.id.til_pwd)
    TextInputLayout tilPwd;
    @InjectView(R.id.et_pwd2)
    EditText etPwd2;
    @InjectView(R.id.til_pwd2)
    TextInputLayout tilPwd2;
    @InjectView(R.id.btn_regist)
    Button btnRegist;
    @InjectView(R.id.register_editor)
    LinearLayout registerEditor;

    private RegisterPresenter mRegisterPresenter;
    private String username;
    private String pwd;
    private String secondPwd;

    private ProgressDialog progressDialog;

    //这边是在初始化布局,布局样式后期也可以做下更改
    @Override
    protected int initContentView() {
        return R.layout.register_activity;

    }

    public RegisterActivity() {
        mRegisterPresenter = new RegisterPresenterImpl(this);
    }

    @OnClick({R.id.registrt_head_back_text, R.id.btn_regist})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registrt_head_back_text:
                // TODO: 2017/2/7 这边做返回的逻辑
                    finish();
                break;
            case R.id.btn_regist:
                username = etUsername.getText().toString().trim();
                pwd = etPwd.getText().toString().trim();
                secondPwd = etPwd2.getText().toString().trim();

                //下面是用户各种点击情况的判断
                if (TextUtils.isEmpty(username)|| TextUtils.isEmpty(pwd)){
                    ToastUtil.showToast("您输入的用户名或密码不合法,请重新输入");
                    break;
                }
                if (tilUsername.getError()!=null){
                    if (tilUsername.getError().equals("用户名不合法")) {
                        ToastUtil.showToast("您输入的用户名或密码不合法,请重新输入");
                        break;
                    }
                }
                if (tilPwd.getError()!=null){
                    if (tilPwd.getError().equals("密码不合法")){
                        ToastUtil.showToast("您输入的用户名或密码不合法,请重新输入");
                        break;
                    }
                }
                if(!pwd.equals(secondPwd)){
                    //tilPwd2.setError("密码不合法");
                    // tilPwd2.setErrorEnabled(true);
                    ToastUtil.showToast("请确认密码输入一致");
                    break;
                }

                regist();
                break;
        }
    }
    //在这边初始化了进度框,但是样式不是很好,后期可以修改一下
    @Override
    protected void initView(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        // progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    protected void initListener() {
        //给用户名添加键盘的监听,根据焦点通过TextInputLayout来验证合法性
        etPwd2.setOnEditorActionListener(this);
        etUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                username = etUsername.getText().toString().trim();
                if (!hasFocus){
                    //这是判断用户名
                    if (!StringUtils.checkUsername(username)){
                        tilUsername.setError("用户名不合法");
                        //这边默认是true的.
                        tilUsername.setErrorEnabled(true);
                        //请求光标焦点,在右侧
                        // etUsername.requestFocus(View.FOCUS_RIGHT);
                        return;
                    }else{
                        tilUsername.setErrorEnabled(false);
                    }
                }
            }
        });

        //给密码框添加键盘的监听,根据焦点通过TextInputLayout来验证合法性
        etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            //不管是获取焦点还是失去焦点都会回调该方法
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                pwd = etPwd.getText().toString().trim();
                if (!hasFocus) { //hasfouse为true,进入了这个框,false,离开
                    //这边判断密码
                    if (!StringUtils.checkPwd(pwd)) {
                        tilPwd.setError("密码不合法");
                        tilPwd.setErrorEnabled(true);
                        // etPwd.requestFocus(View.FOCUS_RIGHT);
                        //如果是错的就不让他去注册
                        return;
                    } else {
                        tilPwd.setErrorEnabled(false);
                    }
                }
            }
        });



    }


    //这是确认密码框,键盘按钮点击确认也能有注册功能
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId()==R.id.et_pwd2){
            if (actionId == EditorInfo.IME_ACTION_DONE){
                //隐藏软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etPwd2.getWindowToken(), 0);

                secondPwd = etPwd2.getText().toString().trim();
                pwd = etPwd.getText().toString().trim();

                //下面是用户各种点击情况的判断
                if (tilUsername.getError()!=null){
                    if (tilUsername.getError().equals("用户名不合法")) {
                        ToastUtil.showToast("您输入的用户名或密码不合法,请重新输入");
                        return false;
                    }
                }
                if (tilPwd.getError()!=null){
                    if (tilPwd.getError().equals("密码不合法")){
                        ToastUtil.showToast("您输入的用户名或密码不合法,请重新输入");
                        return false;
                    }
                }

                //只有当在确定输入框输入了密码后才能去注册
                if(!pwd.equals(secondPwd)){
                    //tilPwd2.setError("密码不合法");
                    // tilPwd2.setErrorEnabled(true);
                    ToastUtil.showToast("请确认密码输入一致");
                    return false;
                }
                regist();
                return  true;
            }
        }
        return false;
    }

    //这是注册的逻辑
    private void regist() {
        //要在注册之前,显示
        showDialog("正在注册");
        //这是注册
        // Log.e("RegisterActivity", "regist: "+username+pwd );
        mRegisterPresenter.regist(username, pwd);

    }

    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        hideDialog();
        RegisterResponse registerResponse =(RegisterResponse)response;
        if ("register".equals(registerResponse.getResponse())) {

            //注册成功
            App.EDIT.putString("userid",registerResponse.getUserInfo().getUserid());
            ToastUtil.showToast("注册成功,请登录");
        }else{
            ToastUtil.showToast("注册失败,失败原因:"
                    +registerResponse.getError_code()+":"+registerResponse.getError());
        }


    }

    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        hideDialog();
        ToastUtil.showToast("请求网络失败,失败原因"+error);
    }


    public void showDialog(String msg){
        progressDialog.setMessage(msg);
        progressDialog.show();

    }
    public void hideDialog(){
        //SystemClock.sleep(2000);
        progressDialog.hide();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
    }
}

