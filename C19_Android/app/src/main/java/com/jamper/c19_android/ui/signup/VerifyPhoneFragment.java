package com.jamper.c19_android.ui.signup;


import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jamper.c19_android.MainActivity;
import com.jamper.c19_android.R;
import com.jamper.c19_android.callbacks.OnItemCallBack;
import com.jamper.c19_android.databinding.FragmentVerifyPhoneBinding;
import com.jamper.c19_android.ui.login.ILogin;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyPhoneFragment extends Fragment implements ILogin {

    FragmentVerifyPhoneBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding =  DataBindingUtil.inflate(inflater,R.layout.fragment_verify_phone, container, false);
        mBinding.setItemClick(this);
        return mBinding.getRoot();
    }



    @Override
    public void onLoginOnClick() {
        Toast.makeText(getContext(), "K calncel", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRegisterOnClick() {
       startActivity(new Intent(getContext(), MainActivity.class));
       requireActivity().finish();
    }
}
