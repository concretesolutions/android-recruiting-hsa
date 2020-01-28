package com.example.androidrecruitchallenge;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidrecruitchallenge.model.Item;
import com.example.androidrecruitchallenge.view.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnListFragmentInteractionListener{
    private HomeFragment.OnListFragmentInteractionListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(Item item) {

    }
}
