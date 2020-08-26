package com.example.task6.model;

import android.annotation.SuppressLint;
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

    public MainModel() {
        apiFactory = ApiFactory.getInstance();
        newsApi = ApiFactory.getNewsApi();
    }

    public List<Story> loadStories(String key, LoadStoryCallback loadStoryCallback) {
        LoadStoriesAsyncTask loadStoriesAsyncTask = new LoadStoriesAsyncTask(loadStoryCallback);
        loadStoriesAsyncTask.execute(key);
        return storyList;
    }
    @SuppressLint("StaticFieldLeak")
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
