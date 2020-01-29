package com.example.androidrecruitchallenge.view.home;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.androidrecruitchallenge.R;
import com.example.androidrecruitchallenge.entity.Item;
import com.example.androidrecruitchallenge.entity.RepositoryList;
import com.example.androidrecruitchallenge.presenter.HomeFragmentPresenterInterface;
import com.example.androidrecruitchallenge.model.HomeFragmentServiceInterface;
import com.example.androidrecruitchallenge.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeFragmentView{

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

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        HomeFragmentServiceInterface homeFragmentService = retrofit.create(HomeFragmentServiceInterface.class);
        Call<RepositoryList> call = homeFragmentService.getGitJavaRepositorys(Constants.LANGUAGE_REQUEST_JAVA, Constants.SORT_MODE_STARTS, actualPageLoad);

        call.enqueue(new Callback<RepositoryList>() {
            @Override
            public void onResponse(Call<RepositoryList> call, Response<RepositoryList> response) {
                repositoryList = response.body();
                if (repositoryList != null && repositoryList.getItems() != null) {
                    for (Item item : repositoryList.getItems()) {
                        listItems.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RepositoryList> call, Throwable t) {
                actualPageLoad--;
                spinner.setVisibility(View.GONE);
            }
        });
    }

    public void addRepositoryItem(Item item){

    }

    public void showHideSpinner(boolean show){

    }

    public void notifyRepositoryListUpdate(){

    }
}
