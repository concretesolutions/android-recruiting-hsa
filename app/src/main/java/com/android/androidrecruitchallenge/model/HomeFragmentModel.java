package com.android.androidrecruitchallenge.model;

import com.android.androidrecruitchallenge.entity.home.RepositoryList;
import com.android.androidrecruitchallenge.model.interfaces.GetServiceInterface;
import com.android.androidrecruitchallenge.model.interfaces.HomeFragmentCallBack;
import com.android.androidrecruitchallenge.model.interfaces.HomeFragmentModelInterface;
import com.android.androidrecruitchallenge.util.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragmentModel implements HomeFragmentModelInterface {
    @Override
    public void getRepositoryList(int actualPageLoad, final HomeFragmentCallBack homeCallBack) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        GetServiceInterface homeFragmentService = retrofit.create(GetServiceInterface.class);
        Call<RepositoryList> call = homeFragmentService.getGitJavaRepositorys(Constants.LANGUAGE_REQUEST_JAVA, Constants.SORT_MODE_STARTS, actualPageLoad);

        call.enqueue(new Callback<RepositoryList>() {
            @Override
            public void onResponse(Call<RepositoryList> call, Response<RepositoryList> response) {
                RepositoryList repositoryList = response.body();
                if(homeCallBack!=null && repositoryList!=null){
                    homeCallBack.onSuccess(repositoryList);
                }
            }

            @Override
            public void onFailure(Call<RepositoryList> call, Throwable t) {
                if(homeCallBack!=null){
                    homeCallBack.onError();
                }
            }
        });
    }
}
