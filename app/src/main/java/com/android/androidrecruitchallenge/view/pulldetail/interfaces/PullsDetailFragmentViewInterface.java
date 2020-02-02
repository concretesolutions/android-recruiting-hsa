package com.android.androidrecruitchallenge.view.pulldetail.interfaces;

import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;

public interface PullsDetailFragmentViewInterface {
    void addPullRequestListItem(PullRequest pullRequest);
    void notifyRequestListUpdate();
    void addEmptyPullRequestList(PullRequest pullRequest);
}
