package com.cadiz.accenture_test.pullrequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cadiz.accenture_test.api.PullRequest
import com.cadiz.accenture_test.api.Repository
import com.cadiz.accenture_test.databinding.PullListItemBinding
import com.cadiz.accenture_test.databinding.RepoListItemBinding
import com.cadiz.accenture_test.repository.RepositoryAdapter

class PullRequestAdapter : ListAdapter<PullRequest, PullRequestAdapter.PullRequestViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<PullRequest>(){

        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onItemClickListener: (PullRequest) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestAdapter.PullRequestViewHolder {
        val binding = PullListItemBinding.inflate(LayoutInflater.from(parent.context))
        return PullRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullRequestAdapter.PullRequestViewHolder, position: Int) {
        val pullRequest : PullRequest = getItem(position)
        holder.bind(pullRequest)

    }

    inner class PullRequestViewHolder(private val binding: PullListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pullRequest: PullRequest) {

            binding.nameTextview.text = pullRequest.title
            binding.descriptionTextView.text = pullRequest.body
            binding.autorTextView.text = pullRequest.login

            Glide.with(itemView.getContext()).load(pullRequest.avatar_url).circleCrop().into(binding.avatarImageView);

            binding.root.setOnClickListener {
                if (::onItemClickListener.isInitialized) {
                    onItemClickListener(pullRequest)
                }
            }
        }
    }
}