package com.tribo_mkt.evaluation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.domain.Post
import com.tribo_mkt.evaluation.respository.Repository
import com.tribo_mkt.evaluation.util.Status
import kotlinx.coroutines.launch

class PostsViewModel(private val repo: Repository) : ViewModel() {

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private var _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>>
        get() = _posts

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    fun getPostsByUserId(userId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch {
            try {
                _posts.value = repo.getPostsByUserId(userId)
                _status.value = Status.SUCCESS
            } catch (t: Throwable) {
                _posts.value = emptyList()
                _error.value = t
            }
        }
    }
}
