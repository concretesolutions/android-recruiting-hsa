package com.cadiz.accenture_test.pullrequest

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cadiz.accenture_test.R
import com.cadiz.accenture_test.api.ApiResponseStatus
import com.cadiz.accenture_test.api.PullRequest
import com.cadiz.accenture_test.api.Repository
import com.cadiz.accenture_test.repository.MainViewModel
import com.cadiz.accenture_test.repository.RepositorySelectListener


class PullRequestFragment : Fragment() {

    private lateinit var viewModel: PullRequestViewModel
    val args: PullRequestFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view :View  =  inflater.inflate(R.layout.fragment_pull_request, container, false)
        val repository = args.repository
        val pullRequestRecyclerView : RecyclerView = view.findViewById(R.id.pullRequestRecyclerView)
        val pullRequestProgressBar : ProgressBar = view.findViewById(R.id.pullRequestProgressBar)
        setupToolbar(view)
        requireActivity().setTitle(R.string.pull_request_fragment_title);

        val adapter =  loadRecyclerView(pullRequestRecyclerView)

        adapter.onItemClickListener = {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse( it.html_url))
            startActivity(browserIntent)
        }

        loadViewModelProvider(repository)
        loadPullRequestList(adapter)
        loadProgressBar(pullRequestProgressBar)
        return view
    }


    private fun loadViewModelProvider(repository: Repository) {
        viewModel = ViewModelProvider(this,
                PullRequestViewModelFactory(
                        requireActivity().application,
                        repository.login,
                        repository.name
                )
        ).get(PullRequestViewModel::class.java)
    }


    private fun setupToolbar(view: View) {
        val toolbar  = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.mainToolbar)
        toolbar.title = getString(R.string.pull_request_fragment_title)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun loadPullRequestList(adapter: PullRequestAdapter) {
        viewModel.pullRequestList.observe(viewLifecycleOwner, Observer {
                pullList: MutableList<PullRequest> ->
            adapter.submitList(pullList)
        })
    }


    private fun loadRecyclerView(pullRequestRecyclerView :RecyclerView): PullRequestAdapter {
        pullRequestRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PullRequestAdapter()
        pullRequestRecyclerView.adapter = adapter
        return adapter;
    }

    private fun loadProgressBar(loadingProgressBar :ProgressBar) {

        viewModel.status.observe(viewLifecycleOwner, Observer { apiresponseStatus: ApiResponseStatus ->
            if (apiresponseStatus == ApiResponseStatus.LOADING) {
                loadingProgressBar.visibility = View.VISIBLE
            } else if (apiresponseStatus == ApiResponseStatus.DONE) {
                loadingProgressBar.visibility = View.GONE
            } else if (apiresponseStatus == ApiResponseStatus.ERROR) {
                loadingProgressBar.visibility = View.GONE
            }
        })

    }

}