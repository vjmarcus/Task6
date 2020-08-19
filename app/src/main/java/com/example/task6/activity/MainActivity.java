package com.example.task6.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.task6.R;
import com.example.task6.data.Story;
import com.example.task6.presenter.MainPresenter;
import com.example.task6.view.MainView;

import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView, View.OnClickListener {

    private static final String TAG = "MyApp";
    @InjectPresenter
    MainPresenter mainPresenter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setText("onCreate");
        textView.setClickable(true);
        textView.setOnClickListener(this);
        loadDefault();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "showMessage: = " + message);
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }

    @Override
    public void showStories(List<Story> storyList) {

    }

    @Override
    public void showTopics() {

    }

    @Override
    public void onClick(View view) {
        mainPresenter.textViewClicked();
    }

    private void loadDefault() {
        mainPresenter.loadStories("BITCOIN");
    }
}