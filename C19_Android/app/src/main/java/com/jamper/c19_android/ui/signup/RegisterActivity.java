
package com.jamper.c19_android.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jamper.c19_android.R;
import com.jamper.c19_android.utils.ActivityUtils;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        new ActivityUtils(this, getSupportFragmentManager()).addFragment(new PersonalDetailsFragment());
    }


    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else getSupportFragmentManager().popBackStack();
    }
}
