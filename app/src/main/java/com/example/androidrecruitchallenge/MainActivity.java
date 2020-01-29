package com.example.androidrecruitchallenge;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidrecruitchallenge.entity.home.Item;
import com.example.androidrecruitchallenge.utils.Constants;
import com.example.androidrecruitchallenge.view.home.HomeFragment;
import com.example.androidrecruitchallenge.view.pulldetail.PullsDetailFragment;
import com.example.androidrecruitchallenge.view.pulldetail.dummy.DummyContent;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnListFragmentInteractionListener,PullsDetailFragment.OnListFragmentInteractionListener {
    private HomeFragment.OnListFragmentInteractionListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onListFragmentInteraction(Item item) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View parentLayout = findViewById(R.id.mainCordinator);
        Snackbar.make(parentLayout, "ESTE ES EL ITEM SELECCIONADO: " + item.getOwner().getLogin(), Snackbar.LENGTH_LONG)
                .show();
        // Create new fragment and transaction
        Fragment newFragment = new PullsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.REPOSITORY_OBJECT_KEY,item);
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.fragmentContainer, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();

    }
    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item){

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
