package com.tribo_mkt.evaluation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.databinding.PostListItemBinding
import com.tribo_mkt.evaluation.domain.Post
import com.tribo_mkt.evaluation.ui.adapter.PostListAdapter.PostListViewHolder

class PostListAdapter :
    ListAdapter<Post, PostListViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    class PostListViewHolder(private var binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {

            binding.post = post

            if (post.comentarios == null) {
                binding.comentarios.visibility = View.GONE
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PostListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostListItemBinding.inflate(layoutInflater, parent, false)
                return PostListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        return PostListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

