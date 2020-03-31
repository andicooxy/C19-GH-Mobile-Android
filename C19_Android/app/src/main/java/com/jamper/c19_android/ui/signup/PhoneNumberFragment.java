package com.jamper.c19_android.ui.signup;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jamper.c19_android.R;
import com.jamper.c19_android.callbacks.OnItemClicked;
import com.jamper.c19_android.databinding.FragmentPhoneNumberBinding;
import com.jamper.c19_android.utils.ActivityUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhoneNumberFragment extends Fragment implements OnItemClicked {

    FragmentPhoneNumberBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding =  DataBindingUtil.inflate(inflater,R.layout.fragment_phone_number, container, false);
        mBinding.setItemClick(this);
        return mBinding.getRoot();
    }

    @Override
    public void execute() {
        new ActivityUtils(getActivity(), getFragmentManager()).replaceFragment(new VerifyPhoneFragment());

    }
}
