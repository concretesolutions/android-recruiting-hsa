package com.cadiz.accenture_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.cadiz.accenture_test.api.Repository
import com.cadiz.accenture_test.databinding.ActivityMainBinding
import com.cadiz.accenture_test.repository.RepositoryFragmentDirections
import com.cadiz.accenture_test.repository.RepositorySelectListener


class MainActivity : AppCompatActivity(),
    RepositorySelectListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun RepositorySelected(repository: Repository) {
        findNavController(R.id.main_navigation_container).navigate(RepositoryFragmentDirections.actionMainListFragmentToPullRequestFragment(repository))
    }
}