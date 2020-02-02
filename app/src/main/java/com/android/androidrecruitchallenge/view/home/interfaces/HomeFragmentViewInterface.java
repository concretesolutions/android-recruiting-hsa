package com.android.androidrecruitchallenge.view.home.interfaces;

import com.android.androidrecruitchallenge.entity.home.Item;

public interface HomeFragmentViewInterface {
    void addRepositoryItem(Item item);
    void notifyRepositoryListUpdate();
}
