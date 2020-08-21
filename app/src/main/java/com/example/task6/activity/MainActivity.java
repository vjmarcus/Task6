package com.example.task6.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.task6.R;
import com.example.task6.RecyclerViewClickListener;
import com.example.task6.ResponseListener;
import com.example.task6.adapter.StoryAdapter;
import com.example.task6.data.Story;
import com.example.task6.presenter.MainPresenter;
import com.example.task6.view.MainView;

import java.util.List;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class MainActivity extends MvpAppCompatActivity implements MainView, AdapterView.OnItemSelectedListener {

    private static final String TAG = "MyApp";
    @InjectPresenter
    MainPresenter mainPresenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewClickListener recyclerViewClickListener;
    private RecyclerView recyclerView;
    private String currentTopic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadDefault();
        init();
        initSwipeRefreshLayout();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mainPresenter.loadStories(adapterView.getSelectedItem().toString());
        Log.d(TAG, "Main onItemSelected: " + adapterView.getSelectedItem().toString());
        currentTopic = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //DO NOTHING
    }

    private void init() {
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<?> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.topics,
                android.R.layout.simple_list_item_1);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        recyclerView = findViewById(R.id.story_recycler);
    }

    @Override
    public void showStories(List<Story> storyList) {
        StoryAdapter storyAdapter = new StoryAdapter(storyList, recyclerViewClickListener);
        recyclerView.setAdapter(storyAdapter);
        initRecyclerViewClickListener();
    }

    @Override
    public void setRefreshingToSwipe(Boolean isRefresh) {
        if (isRefresh){
            swipeRefreshLayout.setRefreshing(true);
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void loadDefault() {
        mainPresenter.loadStories("SOFTWARE");
    }

    private void initSwipeRefreshLayout(){
        swipeRefreshLayout = findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mainPresenter.loadStoriesWithRefresher(currentTopic);
                Log.d(TAG, "onRefresh: swipe");
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    private void initRecyclerViewClickListener() {
        recyclerViewClickListener = new RecyclerViewClickListener() {
            @Override
            public void recyclerViewListClicked(View sharedView, Story story, int position) {
                Log.d(TAG, "recyclerViewListClicked: ");
                goToSecondActivity(sharedView, story, position);
            }
        };
    }

    private void goToSecondActivity(View sharedView, Story story, int position) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("obj", story);
        ActivityOptionsCompat compat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(MainActivity.this, sharedView, "transition");
        startActivity(intent, compat.toBundle());
    }
}