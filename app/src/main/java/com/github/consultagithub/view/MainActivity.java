package com.github.consultagithub.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.github.consultagithub.R;
import com.github.consultagithub.entity.Item;
import com.github.consultagithub.entity.pull.PullRequest;


public class MainActivity extends AppCompatActivity implements RepositoryListFragment.OnListFragmentInteractionListener,PullRepositoryFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(Item item) {

        PullRepositoryFragment pullRepositoryFragment = new PullRepositoryFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("owner", item.getOwner().getLogin());
        bundle.putString("repos", item.getName());
        transaction.replace(R.id.contenedorPrincipal, pullRepositoryFragment);
        transaction.addToBackStack(null);
        pullRepositoryFragment.setArguments(bundle);
        transaction.commit();

    }
    @Override
    public void onListFragmentInteraction(PullRequest item) {

    }
}
