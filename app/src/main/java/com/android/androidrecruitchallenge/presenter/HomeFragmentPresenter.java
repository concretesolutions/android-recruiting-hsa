package com.android.androidrecruitchallenge.presenter;

import com.android.androidrecruitchallenge.entity.home.Item;
import com.android.androidrecruitchallenge.entity.home.RepositoryList;
import com.android.androidrecruitchallenge.model.HomeFragmentModel;
import com.android.androidrecruitchallenge.model.interfaces.HomeFragmentCallBack;
import com.android.androidrecruitchallenge.model.interfaces.HomeFragmentModelInterface;
import com.android.androidrecruitchallenge.presenter.interfaces.HomeFragmentPresenterInterface;
import com.android.androidrecruitchallenge.view.home.interfaces.HomeFragmentViewInterface;

public class HomeFragmentPresenter implements HomeFragmentPresenterInterface {
    private HomeFragmentViewInterface homeView;
    private HomeFragmentModelInterface homeModel;

    public HomeFragmentPresenter(HomeFragmentViewInterface view) {
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
                homeView.notifyRepositoryListUpdate();
            }
        });
    }
}
