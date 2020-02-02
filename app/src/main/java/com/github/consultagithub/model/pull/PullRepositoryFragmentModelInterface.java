package com.github.consultagithub.model.pull;

public interface PullRepositoryFragmentModelInterface {

    void getPulls( String owner, String repos, PullRepositoryCallBack callback);
}
