package com.jamper.c19_android.utils;

import android.app.Dialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jamper.c19_android.R;
import com.jamper.c19_android.callbacks.OnItemClicked;


/**
 *
 */

public class SuccessDialog extends BottomSheetDialogFragment {


    TextView y_Title;
    ImageView check_correct;

    private OnItemClicked callback;
    private String message;

    private BottomSheetBehavior.BottomSheetCallback bottomSheetBehaviourCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };


    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.bottom_success, null);
        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = layoutParams.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(bottomSheetBehaviourCallback);
        }

        y_Title = contentView.findViewById(R.id.y_Title);
        check_correct = contentView.findViewById(R.id.check_correct);

        new ActivityUtils(getActivity()).setImageResource(R.drawable.ic_checked, check_correct);
        y_Title.setText(message);
    }


    public void setCallback(OnItemClicked callback) {
        this.callback = callback;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (callback != null)
            callback.execute();
    }

}
