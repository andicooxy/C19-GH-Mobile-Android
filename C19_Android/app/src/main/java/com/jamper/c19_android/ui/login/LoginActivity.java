package com.jamper.c19_android.ui.login;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;

import com.jamper.c19_android.R;
import com.jamper.c19_android.databinding.ActivityLoginBinding;
import com.jamper.c19_android.ui.base.BaseActivity;
import com.jamper.c19_android.ui.signup.RegisterActivity;

public class LoginActivity extends BaseActivity implements ILogin {

    private ActivityLoginBinding mBinding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new LoginViewModel();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        mBinding.setLoginModel(loginViewModel);
        mBinding.setViewOnClick(LoginActivity.this);
    }


    @Override
    public void onLoginOnClick() {

    }

    @Override
    public void onRegisterOnClick() {
        openActivity(RegisterActivity.class);
    }
}
