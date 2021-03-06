package com.example.task6.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task6.R;
import com.example.task6.data.Story;
import com.example.task6.presenter.SecondPresenter;
import com.example.task6.view.SecondView;
import com.squareup.picasso.Picasso;

import moxy.MvpAppCompatActivity;
import moxy.MvpView;
import moxy.presenter.InjectPresenter;

public class SecondActivity extends MvpAppCompatActivity implements SecondView {

    @InjectPresenter
    SecondPresenter secondPresenter;
    private static final String TAG = "MyApp";
    private TextView titleSecondTextView;
    private TextView sourceNameTextView;
    private TextView descriptionTextView;
    private ImageView picassoImageView;
    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
        loadFromIntent();
        setTextToTextView();
        loadImageToImageViews();
    }

    private void init() {
        titleSecondTextView = findViewById(R.id.titleTextView);
        sourceNameTextView = findViewById(R.id.sourceNameSecondTextView);
        descriptionTextView = findViewById(R.id.descriptionSecondTextView);
        picassoImageView = findViewById(R.id.picassoImageView);
    }

    private void loadFromIntent() {
            Intent intent = getIntent();
            story = (Story) intent.getSerializableExtra("obj");
        if (story != null) {
            Log.d(TAG, "loadFromIntent: " + story.getAuthor());
        }
    }

    @Override
    public void showMessage(String message) {
            Log.d(TAG, "showMessage: this is SECOND");
    }

    private void loadImageToImageViews() {
        Picasso.get().load(story.getUrlToImage())
                .into(picassoImageView);
    }

    private void setTextToTextView() {
        titleSecondTextView.setText(story.getTitle());
        sourceNameTextView.setText(story.getSource().getName());
        descriptionTextView.setText(story.getDescription());
    }
}