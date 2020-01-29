package com.example.androidrecruitchallenge.view.home.interfaces;

import com.example.androidrecruitchallenge.entity.home.Item;

public interface HomeFragmentView {
    void addRepositoryItem(Item item);
    void notifyRepositoryListUpdate();
}
