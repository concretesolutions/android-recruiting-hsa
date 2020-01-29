package com.example.androidrecruitchallenge.presenter;

import com.example.androidrecruitchallenge.entity.home.Item;
import com.example.androidrecruitchallenge.entity.home.RepositoryList;
import com.example.androidrecruitchallenge.model.HomeFragmentModel;
import com.example.androidrecruitchallenge.model.interfaces.HomeFragmentCallBack;
import com.example.androidrecruitchallenge.model.interfaces.HomeFragmentModelInterface;
import com.example.androidrecruitchallenge.presenter.interfaces.HomeFragmentPresenterInterface;
import com.example.androidrecruitchallenge.view.home.interfaces.HomeFragmentView;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface {
    private HomeFragmentView homeView;
    private HomeFragmentModelInterface homeModel;

    public HomeFragmentPresenter(HomeFragmentView view) {
        homeModel = new HomeFragmentModel();
        this.homeView = view;
    }

    public void loadRepositoryList(int actualPage){
        homeModel.getRepositoryList(actualPage, new HomeFragmentCallBack() {
            @Override
            public void onSuccess(RepositoryList list) {
                if(list.getItems()!=null){
                    for(Item item : list.getItems()){
                        homeView.addRepositoryItem(item);
                    }
                    homeView.notifyRepositoryListUpdate();
                }
            }

            @Override
            public void onError() {

            }
        });
    }
}
