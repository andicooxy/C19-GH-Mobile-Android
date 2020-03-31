package com.jamper.c19_android.ui.login;

import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel{
    //Private fields;
    private String phoneNumber;
    private String pin;

    private LoginViewModel mInstance;

    public  LoginViewModel getInstance(){
      if (mInstance == null)
          mInstance = new LoginViewModel();

      return mInstance;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPin() {
        return pin;
    }

}
