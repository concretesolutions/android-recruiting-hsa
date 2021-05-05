package com.cadiz.accenture_test.pullrequest

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PullRequestViewModelFactory (private val application: Application, private val creator: String, private val repo: String):
        ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PullRequestViewModel(application, creator, repo) as T
    }
}
