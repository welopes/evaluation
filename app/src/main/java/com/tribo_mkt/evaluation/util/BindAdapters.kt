package com.tribo_mkt.evaluation.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tribo_mkt.evaluation.domain.User
import com.tribo_mkt.evaluation.ui.adapter.UserListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<User>?) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitList(data) {
        recyclerView.scrollToPosition(0)
    }
}