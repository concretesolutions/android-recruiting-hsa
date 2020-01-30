package com.android.androidrecruitchallenge.model.interfaces;

import com.android.androidrecruitchallenge.entity.home.RepositoryList;
import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;
import com.android.androidrecruitchallenge.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetServiceInterface {
    @GET(Constants.PATH_REPOSITORY_JAVA)
    Call<RepositoryList> getGitJavaRepositorys(@Query("q") String language, @Query("sort") String sortMode,@Query("page") int page);

    @GET(Constants.PATH_REPOS_PULLS)
    Call<List<PullRequest>> getGitJavaRepositorysPulls(@Path("owner") String owner, @Path("repository") String repo);
}
