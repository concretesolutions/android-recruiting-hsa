package com.cadiz.accenture_test.repository

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cadiz.accenture_test.*
import com.cadiz.accenture_test.api.ApiResponseStatus
import com.cadiz.accenture_test.api.PullRequest
import com.cadiz.accenture_test.api.Repository
import com.cadiz.accenture_test.pullrequest.PullRequestAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.ClassCastException


class RepositoryFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var repositorySelectListener: RepositorySelectListener



    override fun onAttach(context: Context) {
        super.onAttach(context)
        repositorySelectListener = try {
            context as RepositorySelectListener
        } catch (e: ClassCastException){
            throw ClassCastException("$context must implement RepositorySelectListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view :View  = inflater.inflate(R.layout.fragment_repository, container, false)
        setupToolbar()
        val repositoryRecyclerView : RecyclerView = view.findViewById(R.id.repositoryRecyclerView)
        val repositoryProgressBar : ProgressBar = view.findViewById(R.id.repositoryProgressBar)
        val adapter =  loadRecyclerView(repositoryRecyclerView)

        adapter.onItemClickListener = {
            repositorySelectListener.RepositorySelected(it)
        }
        loadViewModelProvider()

        loadingPagingFlow(adapter)

        loadProgressBar(repositoryProgressBar)


        return view;
    }

    private fun loadViewModelProvider() {
        viewModel = ViewModelProvider(this,
            RepositoryViewModelFactory(
                requireActivity().application
            )
        ).get(MainViewModel::class.java)
    }


    private fun loadingPagingFlow(adapter: RepositoryAdapter) {
        lifecycleScope.launch {
            viewModel.repoPagingFlow.collect {

                adapter.submitData(it)
            }
        }
    }


    private fun setupToolbar() {
        val toolbar = (activity as MainActivity).findViewById<Toolbar>(R.id.mainToolbar)
        toolbar.title = getString(R.string.app_name)
        toolbar.setTitleTextColor(Color.WHITE)
    }

    private fun loadRecyclerView(repoRecyclerView :RecyclerView): RepositoryAdapter {
        repoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = RepositoryAdapter(requireContext())
        repoRecyclerView.adapter = adapter
       return adapter;
    }

    private fun loadProgressBar(loadingProgressBar :ProgressBar){

        viewModel.status.observe(viewLifecycleOwner, Observer {
                apiresponseStatus: ApiResponseStatus ->
            if(apiresponseStatus == ApiResponseStatus.LOADING){
                loadingProgressBar.visibility = View.VISIBLE
            } else if(apiresponseStatus == ApiResponseStatus.DONE){
                loadingProgressBar.visibility = View.GONE
            }
            else if(apiresponseStatus == ApiResponseStatus.ERROR){
                loadingProgressBar.visibility = View.GONE
            }
        })
    }


}