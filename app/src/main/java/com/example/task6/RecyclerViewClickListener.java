package com.example.task6;

import android.view.View;

import com.example.task6.data.Story;

public interface RecyclerViewClickListener {
    void recyclerViewListClicked(View sharedView, Story story, int position);
}
