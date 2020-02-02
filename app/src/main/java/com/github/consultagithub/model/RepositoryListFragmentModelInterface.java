package com.github.consultagithub.model;

import retrofit2.Callback;

public interface RepositoryListFragmentModelInterface {

    void getPosts(int page, RepositoryListCallBack callback);
}
