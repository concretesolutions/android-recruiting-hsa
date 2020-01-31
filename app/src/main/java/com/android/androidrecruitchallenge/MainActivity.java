package com.android.androidrecruitchallenge;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.android.androidrecruitchallenge.entity.home.Item;
import com.android.androidrecruitchallenge.entity.pullsdetail.PullRequest;
import com.android.androidrecruitchallenge.util.Constants;
import com.android.androidrecruitchallenge.view.home.HomeFragment;
import com.android.androidrecruitchallenge.view.pulldetail.PullsDetailFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnListFragmentInteractionListener,PullsDetailFragment.OnListFragmentInteractionListener {
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

        Fragment newFragment = new PullsDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.REPOSITORY_OBJECT_KEY,item);
        newFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragmentContainer, newFragment);
        transaction.addToBackStack(null);

        transaction.commit();

    }
    @Override
    public void onListFragmentInteraction(PullRequest pullRequest){
        if(pullRequest.getHtmlUrl() !=null && pullRequest.getHtmlUrl().length()>0) {
            String url = pullRequest.getHtmlUrl();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
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

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toolbar.setTitle(getResources().getString(R.string.homeTittle));


            super.onBackPressed();
        }
    }
}
