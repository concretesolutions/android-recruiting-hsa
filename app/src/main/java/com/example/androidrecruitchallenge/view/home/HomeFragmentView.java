package com.example.androidrecruitchallenge.view.home;

import com.example.androidrecruitchallenge.entity.Item;

public interface HomeFragmentView {
    public void addRepositoryItem(Item item);
    public void showHideSpinner(boolean show);
    public void notifyRepositoryListUpdate();
}
