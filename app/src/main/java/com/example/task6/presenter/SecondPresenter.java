package com.example.task6.presenter;

import android.util.Log;

import com.example.task6.view.SecondView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class SecondPresenter extends MvpPresenter<SecondView> {

    private static final String TAG = "MyApp";

    public SecondPresenter() {
        Log.d(TAG, "SecondPresenter:");
        getViewState().showMessage("CONSTRUCTOR");
    }

}
