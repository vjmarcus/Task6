package com.example.task6.view;

import com.example.task6.data.Story;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndStrategy;
import moxy.viewstate.strategy.StateStrategyType;
@StateStrategyType(AddToEndStrategy.class)

public interface MainView extends MvpView {

    void showStories(List<Story> storyList);

    void showTopics();

    void setRefreshingToSwipe(Boolean isRefresh);

}
