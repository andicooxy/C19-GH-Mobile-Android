package com.jamper.c19_android.ui.base;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected void openActivity(Class clazz){
        startActivity(new Intent(this, clazz));
    }

    protected void openCloseActivity(Class clazz){
       this.openActivity(clazz);
        finish();
    }


}
