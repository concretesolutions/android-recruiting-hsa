package com.cadiz.accenture_test.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.cadiz.accenture_test.api.*
import com.cadiz.accenture_test.repository.paging.RepositoryPagingSource
import kotlinx.coroutines.*
import java.net.UnknownHostException
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn


private val Tag = MainViewModel::class.java.simpleName

    class MainViewModel(application: Application): AndroidViewModel(application) {

    private val _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    val repoPagingFlow = Pager(PagingConfig(pageSize = 1)) {
        RepositoryPagingSource()

    }.flow.cachedIn(viewModelScope)


    init {
        viewModelScope.launch {
            try {
                _status.value = ApiResponseStatus.LOADING
                repoPagingFlow
            } catch (e: UnknownHostException) {
                _status.value = ApiResponseStatus.NOT_INTERNET_CONNECTION
                Log.d(Tag, "No internet connection", e)
            }
        }
    }
}