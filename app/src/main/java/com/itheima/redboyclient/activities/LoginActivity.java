package com.itheima.redboyclient.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.VolleyError;
import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.LoginResopnse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.net.HttpLoader;
import org.senydevpkg.net.HttpParams;
import org.senydevpkg.net.resp.IResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by yudenghao on 2017/2/7.
 */

public class LoginActivity extends BaseActivity implements TextView.OnEditorActionListener,View.OnClickListener, HttpLoader.HttpListener {
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.back_home)
    TextView back;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.imageView)
    ImageView imageView;
    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.til_username)
    TextInputLayout tilUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.til_password)
    TextInputLayout tilPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.tv_newuser)
    TextView tvNewuser;
    @InjectView(R.id.tv_backpwd)
    TextView tvBackpwd;
    private ProgressBar progressbar;

    private String username;
    private String password;
    private LoginResopnse loginResopnse;
    @Override
    protected int initContentView() {
        return R.layout.login_activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);

    }

    @Override
    protected void initData() {
        super.initData();
        progressbar = (ProgressBar) findViewById(R.id.pb);

        //获取用户
        username = etUsername.getText().toString().trim();
        //获取密码
        password = etPassword.getText().toString().trim();
        App.EDIT.putString("username",username);
        App.EDIT.putString("password",password);
        App.EDIT.commit();
    }

    @Override
    protected void initListener() {
        super.initListener();
        etPassword.setOnEditorActionListener(this);
        btnLogin.setOnClickListener(this);
        back.setOnClickListener(this);
        tvNewuser.setOnClickListener(this);//注册
        tvBackpwd.setOnClickListener(this);//找回密码
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v.getId()== R.id.et_password) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login();
                return true;
            }
        }
        return false;
    }

    /**
     * 登录逻辑
     */

    private void login() {
        //获取用户
        username = etUsername.getText().toString().trim();
        //获取密码
        password = etPassword.getText().toString().trim();
        if (!checkUsername(username)) {
            tilUsername.setErrorEnabled(true);
            tilUsername.setError("用户名不合法");
            etUsername.requestFocus(View.FOCUS_RIGHT);
            return;
        } else {
            tilUsername.setErrorEnabled(false);
        }

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this,"用户名或者密码不能为空!",Toast.LENGTH_LONG).show();
            progressbar.setVisibility(View.GONE);
            return;
        }

        //http://localhost:8080/RedBabyServer/login?username=xiaowen@itcast.cn&password=123456
        HttpParams params = new HttpParams().put("username",username).put("password",password);
        App.HL.post(ConstantsRedBaby.URL_LOGIN,params, LoginResopnse.class,ConstantsRedBaby.REQUEST_CODE_LOGIN,this);
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()) {
           case R.id.back_home:
            startActivity(MainActivity.class,true);
           case R.id.btn_login:
               login();
               break;
           case R.id.tv_newuser:
              startActivity(RegisterActivity.class,false);
            break;
           case R.id.tv_backpwd:
               break;
           default:
               break;
       }
    }

    public void startActivity(Class clazz,boolean isFinish) {
        if (isFinish) {
            startActivity(new Intent(getApplicationContext(), clazz));
            finish();
        } else {
            startActivity(new Intent(getApplicationContext(), clazz));
        }
    }


    /**
     * 登录成功
     * @param requestCode response对应的requestCode
     * @param response    返回的response
     */
    @Override
    public void onGetResponseSuccess(int requestCode, IResponse response) {
        handleLoginResopnse((LoginResopnse) response);
    }


    /**
     * 登录失败
     * @param requestCode 请求码
     * @param error       异常详情
     */
    @Override
    public void onGetResponseError(int requestCode, VolleyError error) {
        Toast.makeText(this,"登录失败" + error+ "!",Toast.LENGTH_LONG).show();
        progressbar.setVisibility(View.GONE);
    }
    //请求成功
    private void handleLoginResopnse(LoginResopnse response) {
        loginResopnse = response;
        //没做做判断逻辑
        if (loginResopnse.userInfo != null) {
            progressbar.setVisibility(View.GONE);
            startActivity(MainActivity.class,true);
        } else {
            progressbar.setVisibility(View.GONE);
            Toast.makeText(this,"登录失败请检查用户名或者密码!",Toast.LENGTH_LONG).show();
        }

    }

    /**
     * 验证邮箱
     * @param mail
     * @return
     */
    public static boolean checkUsername(String mail) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

}
