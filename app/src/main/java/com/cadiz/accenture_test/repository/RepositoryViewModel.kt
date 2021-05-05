package com.cadiz.accenture_test.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.cadiz.accenture_test.api.*
import kotlinx.coroutines.*
import java.net.UnknownHostException

private val Tag = MainViewModel::class.java.simpleName

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MainRepository()
    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    private var _repositoryList = MutableLiveData<MutableList<Repository>>()
    val repositoryList: LiveData<MutableList<Repository>>
        get() = _repositoryList

    init {
        viewModelScope.launch {
            try {
                _status.value = ApiResponseStatus.LOADING
                _repositoryList.value = repository.fetchRepositories()
                _status.value = ApiResponseStatus.DONE
            } catch (e: UnknownHostException) {
                _status.value = ApiResponseStatus.NOT_INTERNET_CONNECTION
                Log.d(Tag, "No internet connection", e)
            }

        }
    }

}