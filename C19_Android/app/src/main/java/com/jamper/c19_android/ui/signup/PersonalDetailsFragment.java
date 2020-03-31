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
import com.jamper.c19_android.databinding.FragmentPersonalDetailsBinding;
import com.jamper.c19_android.utils.ActivityUtils;
import com.jamper.c19_android.utils.DropDownIds;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalDetailsFragment extends Fragment implements OnItemClicked {

    FragmentPersonalDetailsBinding mBinding;
    PersonalDetailsViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_details, container, false);

        mViewModel = new PersonalDetailsViewModel();
        mBinding.setViewModel(mViewModel);
        mBinding.setDrp(new DropDownIds());
        mBinding.setLifecycleOwner(this);
        mBinding.setItemClick(this);

        return  mBinding.getRoot();
    }

    @Override
    public void execute() {
        new ActivityUtils(getActivity(), getFragmentManager()).replaceFragment(new PhoneNumberFragment());
    }
}
