package com.example.androidrecruitchallenge.presenter;

import android.view.View;

import com.example.androidrecruitchallenge.model.Item;
import com.example.androidrecruitchallenge.model.RepositoryList;

import java.util.ArrayList;

public class HomeFragmentPresenter {
    private View view;
    private RepositoryList repositoryList;

    public HomeFragmentPresenter(View view){
        this.view = view;
        this.repositoryList = new RepositoryList();
        this.repositoryList.setItems(new ArrayList<Item>());
    }

    public void loadListRepository(){

    }
}
