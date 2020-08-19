package com.example.task6.presenter;

import android.util.Log;

import com.example.task6.LoadStoryCallback;
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
        storyList = mainModel.loadStories(key, new LoadStoryCallback() {
            @Override
            public void onCompleteCallback(List<Story> storyList) {
                Log.d(TAG, "Presenter onCompleteCallback: " + storyList.size());
                getViewState().showStories(storyList);
            }
        });
    }

    public void loadStoriesWithRefresher(String key){
        getViewState().setRefreshingToSwipe(true);
        storyList = mainModel.loadStories(key, new LoadStoryCallback() {
            @Override
            public void onCompleteCallback(List<Story> storyList) {
                Log.d(TAG, "Presenter onCompleteCallback: " + storyList.size());
                getViewState().showStories(storyList);
                getViewState().setRefreshingToSwipe(false);
            }
        });
    }
}
