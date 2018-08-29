package com.example.phd.roomsample.utils;

import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

public class ViewHelper {
    public static Snackbar createSnackBar(View _view, String _message, int _length) {
        Snackbar mSnackBar = Snackbar.make(_view, _message, _length);
        View view = mSnackBar.getView();
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
        params.gravity = Gravity.CENTER_VERTICAL;
        view.setLayoutParams(params);

        return mSnackBar;
    }
}