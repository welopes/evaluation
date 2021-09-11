package com.tribo_mkt.evaluation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.databinding.AlbumListItemBinding
import com.tribo_mkt.evaluation.domain.Album
import com.tribo_mkt.evaluation.ui.adapter.AlbumListAdapter.AlbumListViewHolder

class AlbumListAdapter :
    ListAdapter<Album, AlbumListViewHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }

    class AlbumListViewHolder(private var binding: AlbumListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.album = album

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): AlbumListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlbumListItemBinding.inflate(layoutInflater, parent, false)
                return AlbumListViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumListViewHolder {
        return AlbumListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AlbumListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

