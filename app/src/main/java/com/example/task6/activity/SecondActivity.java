package com.example.task6.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.task6.R;
import com.example.task6.view.SecondView;

import moxy.MvpAppCompatActivity;
import moxy.MvpView;

public class SecondActivity extends MvpAppCompatActivity implements SecondView {

    private static final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    public void showMessage() {
        Log.d(TAG, "showMessage: this is SECOND");
    }
}