package com.github.consultagithub.presenter;

import com.github.consultagithub.entity.Item;
import com.github.consultagithub.entity.RepositoryList;
import com.github.consultagithub.model.RepositoryListCallBack;
import com.github.consultagithub.model.RepositoryListFragmentModel;
import com.github.consultagithub.model.RepositoryListFragmentModelInterface;
import com.github.consultagithub.view.interfaces.RepositoryListFragmentViewInterface;


public class RepositoryListFragmentPresenter implements RepositoryListFragmentPresenterInterface {


    RepositoryListFragmentModelInterface model;
    RepositoryListFragmentViewInterface view;

    public RepositoryListFragmentPresenter(RepositoryListFragmentViewInterface view) {
        this.view = view;
        model = new RepositoryListFragmentModel();
    }

    @Override
    public void saveRepository(int page) {

        model.getPosts(page, new RepositoryListCallBack(){

            @Override
            public void OnSucces(RepositoryList list) {
                for(Item item : list.getItems()){
                    view.saveRepositoryList(item);
                }
                view.showRepositoryList();
            }

            @Override
            public void OnError() {

            }
        });
    }
}
