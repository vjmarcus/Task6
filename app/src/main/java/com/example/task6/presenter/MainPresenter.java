package com.example.task6.presenter;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.task6.LoadStoryCallback;
import com.example.task6.activity.MainActivity;
import com.example.task6.activity.SecondActivity;
import com.example.task6.data.Story;
import com.example.task6.model.MainModel;
import com.example.task6.view.MainView;

import java.util.List;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView>  {

    private static final String TAG = "MyApp";
    private MainModel mainModel = new MainModel();
    private List<Story> storyList;


    public MainPresenter() {
        Log.d(TAG, "MainPresenter: constructor");
    }

    public void loadStories(String key) {
        getViewState().startLoading();
        // cuncurrent, or work manager or callable
        storyList = mainModel.loadStories(key, new LoadStoryCallback() {
            @Override
            public void onCompleteCallback(List<Story> storyList) {
                if (storyList != null) {
                    Log.d(TAG, "Presenter onCompleteCallback: " + storyList.size());
                    getViewState().showStories(storyList);
                } else {
                    getViewState().showError("Connection error!");
                }
                // как протестировать?
                getViewState().stopLoading();
            }
        });
    }
}
