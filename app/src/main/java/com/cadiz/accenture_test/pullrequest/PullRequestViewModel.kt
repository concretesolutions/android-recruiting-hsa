package com.cadiz.accenture_test.pullrequest

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.cadiz.accenture_test.api.ApiResponseStatus
import com.cadiz.accenture_test.api.PullRequest
import kotlinx.coroutines.launch
import java.net.UnknownHostException


private val Tag = PullRequestViewModel::class.java.simpleName

class PullRequestViewModel(application: Application, creator: String, repo: String): AndroidViewModel(application){

    private val repository = PullRequestRepository()

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    private var _pullRequestList = MutableLiveData<MutableList<PullRequest>>()
    val pullRequestList: LiveData<MutableList<PullRequest>>
        get() = _pullRequestList

    init {
        viewModelScope.launch {

            try {
                _status.value = ApiResponseStatus.LOADING
                _pullRequestList.value = repository.fetchPullRequest(creator, repo)
                _status.value = ApiResponseStatus.DONE
            } catch (e: UnknownHostException) {
                _status.value = ApiResponseStatus.NOT_INTERNET_CONNECTION
                Log.d(Tag, "No internet connection", e)
            }

        }
    }
}