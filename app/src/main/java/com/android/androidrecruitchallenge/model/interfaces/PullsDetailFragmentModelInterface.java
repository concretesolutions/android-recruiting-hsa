package com.android.androidrecruitchallenge.model.interfaces;

public interface PullsDetailFragmentModelInterface {
    void getPullRequestList(String owner, String repository, PullsDetailFragmentCallBack callBack);
}
