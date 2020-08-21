package com.example.task6.presenter;

import com.example.task6.LoadStoryCallback;
import com.example.task6.data.Source;
import com.example.task6.data.Story;
import com.example.task6.model.MainModel;
import com.example.task6.view.MainView$$State;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mainPresenter  = new MainPresenter();
        mainPresenter.setViewState(mainViewState);
    }

    // settings of fucking object
    // when(<mockObject>.<method>(<arguments>)).thenReturn(<return>)
    @Test
    public void loadStories() {
        List<Story> fakeStoryList = getFakeStoryList();
        when(mainModel.loadStories(KEY, loadStoryCallback)).thenReturn(fakeStoryList);
        mainPresenter.loadStories(KEY);
        // verify(<mockObject>).<method>(<arguments>)
        verify(mainViewState).showStories(fakeStoryList);
    }

    @Test
    public void loadStoriesWithRefresher() {
    }

    private List<Story> getFakeStoryList () {
        List<Story> storyList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            storyList.add(new Story(new Source("sourceName"), "author" + i, "title" + i,
                    "desc" + i, "urlToImage" + i, "publishedAt" + i));
        }
        return storyList;
    }
}