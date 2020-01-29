package com.example.androidrecruitchallenge;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidrecruitchallenge.entity.Item;
import com.example.androidrecruitchallenge.view.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnListFragmentInteractionListener{
    private HomeFragment.OnListFragmentInteractionListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onListFragmentInteraction(Item item) {
        View parentLayout = findViewById(R.id.mainCordinator);
        Snackbar.make(parentLayout, "ESTE ES EL ITEM SELECCIONADO: " + item.getOwner().getLogin(), Snackbar.LENGTH_LONG)
                .show();


    }
}
