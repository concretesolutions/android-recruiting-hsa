package com.android.androidrecruitchallenge.view.pulldetail;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.androidrecruitchallenge.R;
import com.android.androidrecruitchallenge.entity.home.Item;
import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;
import com.android.androidrecruitchallenge.presenter.PullsDetailFragmentPresenter;
import com.android.androidrecruitchallenge.presenter.interfaces.PullsDetailFragmentPresenterInterface;
import com.android.androidrecruitchallenge.util.Constants;
import com.android.androidrecruitchallenge.view.pulldetail.interfaces.PullsDetailFragmentViewInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PullsDetailFragment extends Fragment implements PullsDetailFragmentViewInterface {
    private OnListFragmentInteractionListener mListener;
    private final List<PullRequest> pullList = new ArrayList<>();
    private PullsDetailRecyclerViewAdapter adapter;
    private PullsDetailFragmentPresenterInterface presenter;
    private ProgressBar spinner;
    private Item repository;

    public PullsDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            repository = (Item) getArguments().getSerializable(Constants.REPOSITORY_OBJECT_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pulldetail_list, container, false);

        Context context = view.getContext();
        adapter = new PullsDetailRecyclerViewAdapter( pullList, mListener, this.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.pullsRequestList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        presenter = new PullsDetailFragmentPresenter(this);
        spinner = view.findViewById(R.id.progressBarPullsRequest);
        spinner.setVisibility(View.GONE);

        if(repository!=null && repository.getOwner()!=null) {
            if(pullList.isEmpty()) {
                spinner.setVisibility(View.VISIBLE);
                presenter.loadPullRequestList(repository.getOwner().getLogin(), repository.getName());
            }
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(PullRequest pullRequest);
    }

    @Override
    public void addPullRequestListItem(PullRequest pullRequest) {
        pullList.add(pullRequest);
    }

    @Override
    public void notifyRequestListUpdate() {
        spinner.setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void addEmptyPullRequestList(PullRequest pullRequest) {
        pullRequest.setBody(getResources().getString(R.string.pullRequestListEmptyDesc));
        pullList.add(pullRequest);
    }
}
