package com.github.consultagithub.view.interfaces;

import com.github.consultagithub.entity.pull.PullRequest;

public interface PullRepositoryFragmentViewInterface {

    void saveRepositoryList(PullRequest item);
    void showRepositoryList();
}
