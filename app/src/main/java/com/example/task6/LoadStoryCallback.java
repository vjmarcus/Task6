package com.example.task6;

import com.example.task6.data.Story;

import java.util.List;

public interface LoadStoryCallback {
    void onCompleteCallback(List<Story> storyList);
}
