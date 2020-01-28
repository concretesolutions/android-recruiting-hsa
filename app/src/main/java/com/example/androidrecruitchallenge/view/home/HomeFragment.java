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

import com.example.androidrecruitchallenge.R;
import com.example.androidrecruitchallenge.model.Item;
import com.example.androidrecruitchallenge.model.RepositoryList;
import com.example.androidrecruitchallenge.presenter.GetService;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class HomeFragment extends Fragment {

    private static OnListFragmentInteractionListener mListener;
    private static RepositoryList repositoryList;
    private static List<Item> listItems = new ArrayList<>();
    private static HomeRecyclerViewAdapter adapter;
    //private ProgressBar spinnerHome;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static HomeFragment newInstance(int columnCount) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            adapter = new HomeRecyclerViewAdapter(listItems, mListener, this.getContext());
            recyclerView.setAdapter(adapter);
            //spinnerHome = (ProgressBar)view.findViewById(R.id.progressBarHome);
            if (listItems.isEmpty()){
                getGitRepositoryList();
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Item item);
    }

    private void getGitRepositoryList(){
        //spinnerHome.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/search/").addConverterFactory(GsonConverterFactory.create()).build();

        GetService getService  = retrofit.create(GetService.class);
        Call<RepositoryList> call = getService.getGitJavaRepositorys();

        call.enqueue(new Callback<RepositoryList>() {
            @Override
            public void onResponse(Call<RepositoryList> call, Response<RepositoryList> response) {
                repositoryList = response.body();
                for(Item item : repositoryList.getItems()){
                    listItems.add(item);
                }
                adapter.notifyDataSetChanged();
                //spinnerHome.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<RepositoryList> call, Throwable t) {
                //spinnerHome.setVisibility(View.GONE);
            }
        });
    }
}
