package com.github.consultagithub.model;

import com.github.consultagithub.MyItemRecyclerViewAdapter;
import com.github.consultagithub.constant.Constants;
import com.github.consultagithub.entity.Item;
import com.github.consultagithub.entity.RepositoryList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryListFragmentModel implements RepositoryListFragmentModelInterface {

    public void getPosts(int page, final RepositoryListCallBack callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_URI)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetRepositoryModel postService = retrofit.create(GetRepositoryModel.class);
        Call<RepositoryList> call = postService.getRepo(Constants.Q_PARAMETER, Constants.SORT, page);

        call.enqueue(new Callback<RepositoryList>() {
            @Override
            public void onResponse(Call<RepositoryList> call, Response<RepositoryList> response) {
                if(response != null){
                    callback.OnSucces(response.body());
                }
            }

            @Override
            public void onFailure(Call<RepositoryList> call, Throwable t) {
            }
        });
    }
}
