package com.example.task6.presenter;

import com.example.task6.LoadStoryCallback;
import com.example.task6.data.Source;
import com.example.task6.data.Story;
import com.example.task6.data.StoryList;
import com.example.task6.model.MainModel;
import com.example.task6.view.MainView;
import com.example.task6.view.MainView$$State;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainModel mainModel;
    @Mock
    LoadStoryCallback loadStoryCallback;
    @Mock
    MainView$$State mainViewState;

    private MainPresenter mainPresenter;
    private final String KEY = "key";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainPresenter = new MainPresenter();
        mainPresenter.setViewState(mainViewState);
    }

    @Test
    public void loadStoriesSuccess() {
        /* settings mockito object
         * when(<mockObject>.<method>(<arguments>)).thenReturn(<return>)
         */
        List<Story> fakeStoryList = getFakeStoryList();
        when(mainModel.loadStories(KEY, loadStoryCallback)).thenReturn(fakeStoryList);
        mainPresenter.loadStories(KEY);
        verify(mainViewState).startLoading();
        verify(mainViewState, never()).showError(anyString());
        verify(mainViewState).showStories(fakeStoryList);

//        not working
//        verify(mainViewState).showStories(fakeStoryList);
//        in callback
//        verify(mainViewState).stopLoading();
    }



    private List<Story> getFakeStoryList() {
        List<Story> storyList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            storyList.add(new Story(new Source("sourceName"), "author" + i, "title" + i,
                    "desc" + i, "urlToImage" + i, "publishedAt" + i));
        }
        return storyList;
    }
}