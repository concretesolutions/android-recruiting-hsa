package com.github.consultagithub.model.pull;

import com.github.consultagithub.entity.RepositoryList;
import com.github.consultagithub.entity.pull.PullRequest;

import java.util.List;

public interface PullRepositoryCallBack {

    void OnSucces(List<PullRequest> list);
    void OnError();
}
