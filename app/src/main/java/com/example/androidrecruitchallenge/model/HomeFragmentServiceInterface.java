package com.example.androidrecruitchallenge.model;

import com.example.androidrecruitchallenge.entity.RepositoryList;
import com.example.androidrecruitchallenge.utils.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HomeFragmentServiceInterface {
    @GET(Constants.PATH_REPOSITORY_JAVA)
    Call<RepositoryList> getGitJavaRepositorys(@Query("q") String language, @Query("sort") String sortMode,@Query("page") int page);

    @GET(Constants.PATH_REPOS_PULLS)
    Call<RepositoryList> getGitJavaRepositorysPulls(@Path("owner") String owner, @Path("repository") String repo);
}
