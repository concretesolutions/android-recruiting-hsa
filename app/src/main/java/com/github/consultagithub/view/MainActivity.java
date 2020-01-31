package com.github.consultagithub.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.github.consultagithub.R;
import com.github.consultagithub.entity.Item;
import com.github.consultagithub.entity.pull.PullRequest;


public class MainActivity extends AppCompatActivity implements RepositoryListFragment.OnListFragmentInteractionListener,PullRepositoryFragment.OnListFragmentInteractionListener {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onListFragmentInteraction(Item item) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(item.getName());
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

    @Override
    public void onBackPressed(){
        if(getSupportFragmentManager().getBackStackEntryCount() == 1){
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar.setTitle("Github JavaPop");
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
