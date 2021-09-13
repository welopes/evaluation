package com.welopes.evaluation.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.welopes.evaluation.domain.Album
import com.welopes.evaluation.domain.Post
import com.welopes.evaluation.domain.User
import com.welopes.evaluation.ui.adapter.AlbumListAdapter
import com.welopes.evaluation.ui.adapter.PostListAdapter
import com.welopes.evaluation.ui.adapter.UserListAdapter

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