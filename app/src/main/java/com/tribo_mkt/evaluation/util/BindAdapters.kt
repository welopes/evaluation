package com.tribo_mkt.evaluation.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.domain.Album
import com.tribo_mkt.evaluation.domain.Post
import com.tribo_mkt.evaluation.domain.User
import com.tribo_mkt.evaluation.ui.adapter.AlbumListAdapter
import com.tribo_mkt.evaluation.ui.adapter.PostListAdapter
import com.tribo_mkt.evaluation.ui.adapter.UserListAdapter

@BindingAdapter("userListData")
fun bindUserRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("postListData")
fun bindPostRecyclerView(recyclerView: RecyclerView, data: List<Post>?) {
    val adapter = recyclerView.adapter as PostListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("albumListData")
fun bindAlbumRecyclerView(recyclerView: RecyclerView, data: List<Album>?) {
    val adapter = recyclerView.adapter as AlbumListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}