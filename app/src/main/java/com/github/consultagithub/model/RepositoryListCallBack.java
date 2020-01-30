package com.github.consultagithub.model;

import com.github.consultagithub.entity.RepositoryList;

public interface RepositoryListCallBack {

    void OnSucces(RepositoryList list);
    void OnError();
}
