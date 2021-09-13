package com.welopes.evaluation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welopes.evaluation.domain.Post
import com.welopes.evaluation.respository.Repository
import com.welopes.evaluation.util.Status
import kotlinx.coroutines.launch

class PostsViewModel(private val repo: Repository) : ViewModel() {

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private var _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    fun getPostsByUserId(userId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch {
            try {
                _posts.value = repo.getPostsByUserId(userId)
                _status.value = Status.SUCCESS
            } catch (t: Throwable) {
                _posts.value = emptyList()
                _status.value = Status.ERROR
            }
        }
    }
}
