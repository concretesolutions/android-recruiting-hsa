package com.android.androidrecruitchallenge.view.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.androidrecruitchallenge.R;
import com.android.androidrecruitchallenge.entity.home.Item;
import com.android.androidrecruitchallenge.entity.home.RepositoryList;
import com.android.androidrecruitchallenge.presenter.HomeFragmentPresenter;
import com.android.androidrecruitchallenge.presenter.interfaces.HomeFragmentPresenterInterface;
import com.android.androidrecruitchallenge.view.home.interfaces.HomeFragmentViewInterface;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements HomeFragmentViewInterface {

    private static OnListFragmentInteractionListener mListener;
    private static RepositoryList repositoryList;
    private static List<Item> listItems = new ArrayList<>();
    private static HomeRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private HomeFragmentPresenterInterface presenter;
    private ProgressBar spinner;
    private static int actualPageLoad = 0;
    private static int pastVisiblesItems, visibleItemCount, totalItemCount;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);


        Context context = view.getContext();
        linearLayoutManager = new LinearLayoutManager(context);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new HomeRecyclerViewAdapter(listItems, mListener, this.getContext());
        recyclerView.setAdapter(adapter);

        spinner = (ProgressBar) view.findViewById(R.id.progressBarHome);
        spinner.setVisibility(View.GONE);

        presenter = new HomeFragmentPresenter(this);

        if (listItems.isEmpty()) {
            getGitRepositoryList();
        }

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        getGitRepositoryList();
                    }
                }
            }
        });
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
        void onListFragmentInteraction(Item item);
    }

    private void getGitRepositoryList() {
        spinner.setVisibility(View.VISIBLE);
        actualPageLoad++;
        presenter.loadRepositoryList(actualPageLoad);
    }

    public void addRepositoryItem(Item item){
        listItems.add(item);
    }

    public void notifyRepositoryListUpdate(){
        adapter.notifyDataSetChanged();
        spinner.setVisibility(View.GONE);
    }
}
