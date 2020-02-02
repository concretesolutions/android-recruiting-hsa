package com.github.consultagithub.model;

import com.github.consultagithub.constant.Constants;
import com.github.consultagithub.entity.RepositoryList;
import com.github.consultagithub.entity.pull.PullRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetRepositoryModel {

    String API_ROUTE = Constants.API_ROUTE;
    String API_PULL = Constants.API_PULL;
    @GET(API_ROUTE)
    Call<RepositoryList> getRepo(@Query("q") String language, @Query("sort") String sorts, @Query("page") int page);

    @GET(API_PULL)
    Call<List<PullRequest>> getPullRepo(@Path("owner") String owner, @Path("repository") String repo);
}
