package com.example.androidrecruitchallenge.model.interfaces;

import com.example.androidrecruitchallenge.entity.home.RepositoryList;

public interface HomeFragmentCallBack {
    void onSuccess(RepositoryList list);
    void onError();
}
