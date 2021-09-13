package com.welopes.evaluation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.welopes.evaluation.R
import com.welopes.evaluation.databinding.UserListItemBinding
import com.welopes.evaluation.domain.User
import com.welopes.evaluation.ui.adapter.UserListAdapter.UserListViewHolder
import java.util.*

class UserListAdapter(
    private val albumClickListener: AlbumClickListener,
    private val postClickListener: PostClickListener
) :
    ListAdapter<User, UserListViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    class UserListViewHolder(private var binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            albumClickListener: AlbumClickListener,
            postClickListener: PostClickListener,
            user: User,
            position: Int
        ) {

            binding.user = user

            binding.apply {
                letra.text = user.name.substring(0, 2).uppercase(Locale.getDefault())
                if ((position - 1) % 2 == 0) {
                    binding.fundo.setBackgroundColor(
                        ContextCompat.getColor(
                            fundo.context,
                            R.color.fundo
                        )
                    )
                }
            }

            binding.albumClickListener = albumClickListener
            binding.postClickListener = postClickListener

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): UserListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserListItemBinding.inflate(layoutInflater, parent, false)
                return UserListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        return UserListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        holder.bind(albumClickListener, postClickListener, getItem(position), position)
    }
}

class AlbumClickListener(val clickListener: (user: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}

class PostClickListener(val clickListener: (user: User) -> Unit) {
    fun onClick(user: User) = clickListener(user)
}

