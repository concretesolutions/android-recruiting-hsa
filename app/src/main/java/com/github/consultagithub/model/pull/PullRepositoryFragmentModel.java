package com.github.consultagithub.model.pull;

import com.github.consultagithub.constant.Constants;
import com.github.consultagithub.entity.pull.PullRequest;
import com.github.consultagithub.model.GetRepositoryModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PullRepositoryFragmentModel implements PullRepositoryFragmentModelInterface {

    public void getPulls(String owner, String repos, final PullRepositoryCallBack callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRepositoryModel postService = retrofit.create(GetRepositoryModel.class);
        Call<List<PullRequest>> call = postService.getPullRepo(owner, repos);

        call.enqueue(new Callback<List<PullRequest>>() {
            @Override
            public void onResponse(Call<List<PullRequest>> call, Response<List<PullRequest>> response) {
                if(response != null){
                    callback.OnSucces(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<PullRequest>> call, Throwable t) {
            }
        });
    }
}
