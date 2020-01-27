package com.example.androidrecruitchallenge.presenter;

import com.example.androidrecruitchallenge.model.RepositoryList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetService {
    String API_ROUTE = "repositories?q=language:Java&sort=stars&page=1";
    @GET(API_ROUTE)
    Call<RepositoryList> getGitJavaRepositorys();
}
