package com.android.androidrecruitchallenge.model.interfaces;

import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;

import java.util.List;

public interface PullsDetailFragmentCallBack {
    void onSuccess(List<PullRequest> pullRequestList);
    void onError();
}
