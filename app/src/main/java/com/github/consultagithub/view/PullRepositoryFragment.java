package com.github.consultagithub.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.consultagithub.MyPullRepositoryRecyclerViewAdapter;
import com.github.consultagithub.R;
import com.github.consultagithub.dummy.DummyContent;
import com.github.consultagithub.dummy.DummyContent.DummyItem;
import com.github.consultagithub.entity.Item;
import com.github.consultagithub.entity.pull.PullRequest;
import com.github.consultagithub.presenter.PullRepositorioFragmentPresenter;
import com.github.consultagithub.presenter.PullRepositorioFragmentPresenterInterface;
import com.github.consultagithub.presenter.RepositoryListFragmentPresenter;
import com.github.consultagithub.presenter.RepositoryListFragmentPresenterInterface;
import com.github.consultagithub.view.interfaces.PullRepositoryFragmentViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class PullRepositoryFragment extends Fragment implements PullRepositoryFragmentViewInterface {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    List<PullRequest> pullRequests = new ArrayList<>();
    PullRepositorioFragmentPresenterInterface presenter;
    MyPullRepositoryRecyclerViewAdapter adapter;
    String owner;
    String repos;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PullRepositoryFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PullRepositoryFragment newInstance(int columnCount) {
        PullRepositoryFragment fragment = new PullRepositoryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            owner = getArguments().getString("owner");
            repos = getArguments().getString("repos");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pullrepository_list, container, false);

        // Set the adapter

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.listPull);
        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }
        adapter = new MyPullRepositoryRecyclerViewAdapter(pullRequests, mListener);
        recyclerView.setAdapter(adapter);

        presenter = new PullRepositorioFragmentPresenter(this);
        presenter.saveRepository(owner, repos);
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
    public void saveRepositoryList(PullRequest item) {
        pullRequests.add(item);
    }

    @Override
    public void showRepositoryList() {
        adapter.notifyDataSetChanged();
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
        void onListFragmentInteraction(PullRequest item);
    }
}
