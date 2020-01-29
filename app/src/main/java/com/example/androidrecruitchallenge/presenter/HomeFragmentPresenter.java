package com.example.androidrecruitchallenge.presenter;

import com.example.androidrecruitchallenge.model.HomeFragmentModel;
import com.example.androidrecruitchallenge.model.HomeFragmentModelInterface;
import com.example.androidrecruitchallenge.view.home.HomeFragmentView;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface{
    private HomeFragmentView homeView;
    private HomeFragmentModelInterface homeModel;

    public HomeFragmentPresenter(HomeFragmentView view) {
        homeModel = new HomeFragmentModel();
        this.homeView = view;
    }

    public void loadRepositoryList(){

    }
}
