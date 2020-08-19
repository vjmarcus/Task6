package com.example.task6.model;

import android.os.AsyncTask;
import android.util.Log;

import com.example.task6.LoadStoryCallback;
import com.example.task6.ResponseListener;
import com.example.task6.api.ApiFactory;
import com.example.task6.api.NewsApi;
import com.example.task6.data.Story;
import com.example.task6.data.StoryList;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MainModel {

    private static final String TAG = "MyApp";
    private List<Story> storyList;
    private ApiFactory apiFactory;
    private NewsApi newsApi;
    private ResponseListener responseListener;
    private LoadStoryCallback loadStoryCallback;

    public MainModel() {
        apiFactory = ApiFactory.getInstance();
        newsApi = ApiFactory.getNewsApi();
    }

    public List<Story> loadStories(String key, LoadStoryCallback loadStoryCallback) {
        LoadStoriesAsyncTask loadStoriesAsyncTask = new LoadStoriesAsyncTask(loadStoryCallback);
        loadStoriesAsyncTask.execute(key);
        return storyList;
    }

    //    public List<Story> getStories(String key) {
////        Call<StoryList> call = newsApi.getPostsByDate(key, ApiFactory.getCurrentDate(),
////                ApiFactory.getCurrentDate(), 20, "en", ApiFactory.API_KEY);
////        call.enqueue(new Callback<StoryList>() {
////            @Override
////            public void onResponse(@NonNull Call<StoryList> call, @NonNull Response<StoryList> response) {
////                Log.d(TAG, "Model onResponse: " + response);
////                StoryList articlesList = response.body();
////                if (articlesList != null) {
////                    storyList = articlesList.getArticles();
////                    initRecyclerViewClickListener();
////                    initRecycler();
////                    Log.d(TAG, "onResponse: " + storyList.size());
////                    responseListener.responseReceived(true);
////                    responseListener.responseReceived(true);
////                }
////            }
////
////            @EverythingIsNonNull
////            @Override
////            public void onFailure(Call<StoryList> call, Throwable t) {
////                Log.d(TAG, "model onFailure: " + t.getMessage());
////            }
////        });
////        return storyList;
////    }
    class LoadStoriesAsyncTask extends AsyncTask<String, Void, List<Story>> {

        private LoadStoryCallback loadStoryCallback;

    public LoadStoriesAsyncTask(LoadStoryCallback loadStoryCallback) {
        this.loadStoryCallback = loadStoryCallback;
    }

    @Override
    protected List<Story> doInBackground(String... strings) {
        Call<StoryList> call = newsApi.getPostsByDate(strings[0], ApiFactory.getCurrentDate(),
                ApiFactory.getCurrentDate(), 20, "en", ApiFactory.API_KEY);
        StoryList articlesList = null;
        try {
            articlesList = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (articlesList != null) {
            storyList = articlesList.getArticles();
            Log.d(TAG, "Model doInBackground: " + storyList.size());
        }
        return storyList;
    }
    @Override
    protected void onPostExecute(List<Story> stories) {
        super.onPostExecute(stories);
        loadStoryCallback.onCompleteCallback(stories);
    }
}
}
