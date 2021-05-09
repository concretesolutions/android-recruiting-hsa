package com.cadiz.accenture_test.repository
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cadiz.accenture_test.api.Repository
import com.cadiz.accenture_test.databinding.RepoListItemBinding

class RepositoryAdapter(context :Context): PagingDataAdapter<Repository, RepositoryAdapter.RepositoryViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Repository>(){

        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }

    lateinit var onItemClickListener: (Repository) -> Unit


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = RepoListItemBinding.inflate(LayoutInflater.from(parent.context))
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository : Repository? = getItem(position)
        holder.bind(repository)

    }

    inner class RepositoryViewHolder(private val binding: RepoListItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(repository: Repository?){
            binding.nameTextview.text = repository?.name
            binding.autorTextView.text = repository?.autor
            binding.descriptionTextView.text = repository?.description
            binding.forkTextView.text = repository?.fork_count.toString()
            binding.starTextView.text = repository?.star_count.toString()

            Glide.with(itemView.getContext()).load(repository?.avatar_url).circleCrop().into(binding.repositoryImageView);

            binding.root.setOnClickListener{
                if(::onItemClickListener.isInitialized){
                    onItemClickListener(repository!!)
                }
            }
        }
    }
}