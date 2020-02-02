package com.github.consultagithub.presenter;

import com.github.consultagithub.entity.pull.PullRequest;
import com.github.consultagithub.entity.pull.User;
import com.github.consultagithub.model.pull.PullRepositoryCallBack;
import com.github.consultagithub.model.pull.PullRepositoryFragmentModel;
import com.github.consultagithub.model.pull.PullRepositoryFragmentModelInterface;
import com.github.consultagithub.view.interfaces.PullRepositoryFragmentViewInterface;

import java.util.List;

public class PullRepositorioFragmentPresenter implements PullRepositorioFragmentPresenterInterface {

    PullRepositoryFragmentModelInterface model;
    PullRepositoryFragmentViewInterface view;

    public PullRepositorioFragmentPresenter(PullRepositoryFragmentViewInterface view) {
        this.view = view;
        model = new PullRepositoryFragmentModel();
    }

    @Override
    public void saveRepository(String owner, String repos) {

        model.getPulls(owner,repos, new PullRepositoryCallBack(){

            @Override
            public void OnSucces(List<PullRequest> list) {

                if(!list.isEmpty()){
                for(PullRequest item : list){
                    view.saveRepositoryList(item);
                }}
                else{
                    PullRequest pull = new PullRequest();
                    pull.setTitle("No existe un PullRequest asociado a este usuario");
                    User user = new User();
                    user.setAvatarUrl("");
                    user.setLogin("");
                    pull.setBody("");
                    pull.setUser(user);
                    view.saveRepositoryList(pull);
                }
                view.showRepositoryList();
            }

            @Override
            public void OnError() {

            }
        });

    }
}
