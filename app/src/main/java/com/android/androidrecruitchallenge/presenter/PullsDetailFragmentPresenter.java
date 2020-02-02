package com.android.androidrecruitchallenge.presenter;

import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;
import com.android.androidrecruitchallenge.entity.pullsdetail.User;
import com.android.androidrecruitchallenge.model.PullsDetailFragmentModel;
import com.android.androidrecruitchallenge.model.interfaces.PullsDetailFragmentCallBack;
import com.android.androidrecruitchallenge.model.interfaces.PullsDetailFragmentModelInterface;
import com.android.androidrecruitchallenge.presenter.interfaces.PullsDetailFragmentPresenterInterface;
import com.android.androidrecruitchallenge.view.pulldetail.interfaces.PullsDetailFragmentViewInterface;

import java.util.List;

public class PullsDetailFragmentPresenter implements PullsDetailFragmentPresenterInterface {
    private PullsDetailFragmentViewInterface view;
    private PullsDetailFragmentModelInterface model;

    public PullsDetailFragmentPresenter(PullsDetailFragmentViewInterface view){
        this.view = view;
        model = new PullsDetailFragmentModel();
    }
    @Override
    public void loadPullRequestList(String owner, String repository) {
        model.getPullRequestList(owner, repository, new PullsDetailFragmentCallBack() {
            @Override
            public void onSuccess(List<PullRequest> pullRequestList) {
                if(pullRequestList.isEmpty()){
                    PullRequest pullRequestEmpty = new PullRequest();
                    User user = new User();
                    user.setAvatarUrl("");
                    user.setLogin("");
                    pullRequestEmpty.setState("closed");
                    pullRequestEmpty.setUser(user);
                    pullRequestEmpty.setTitle("");
                    pullRequestEmpty.setCreatedAt("");
                    pullRequestEmpty.setBody("");
                    view.addEmptyPullRequestList(pullRequestEmpty);
                }else{
                    for(PullRequest pullRequest : pullRequestList){
                        view.addPullRequestListItem(pullRequest);
                    }
                }
                view.notifyRequestListUpdate();
            }

            @Override
            public void onError() {
                view.notifyRequestListUpdate();
            }
        });
    }
}
