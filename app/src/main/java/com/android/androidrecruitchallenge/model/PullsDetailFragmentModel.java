package com.android.androidrecruitchallenge.model;

import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;
import com.android.androidrecruitchallenge.model.interfaces.GetServiceInterface;
import com.android.androidrecruitchallenge.model.interfaces.PullsDetailFragmentCallBack;
import com.android.androidrecruitchallenge.model.interfaces.PullsDetailFragmentModelInterface;
import com.android.androidrecruitchallenge.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullsDetailFragmentModel implements PullsDetailFragmentModelInterface {
    @Override
    public void getPullRequestList(String owner, String repository, final PullsDetailFragmentCallBack pullsDetailCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        GetServiceInterface homeFragmentService = retrofit.create(GetServiceInterface.class);
        Call<List<PullRequest>> call = homeFragmentService.getGitJavaRepositorysPulls(owner,repository);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                List<PullRequest> pullRequestList = response.body();
                if(pullsDetailCallBack!=null && pullRequestList!=null){
                    pullsDetailCallBack.onSuccess(pullRequestList);
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
                if(pullsDetailCallBack!=null){
                    pullsDetailCallBack.onError();
                }
            }
        });
    }
}
