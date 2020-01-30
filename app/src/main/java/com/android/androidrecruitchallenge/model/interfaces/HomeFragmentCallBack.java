package com.android.androidrecruitchallenge.model.interfaces;

import com.android.androidrecruitchallenge.entity.home.RepositoryList;

public interface HomeFragmentCallBack {
    void onSuccess(RepositoryList list);
    void onError();
}
