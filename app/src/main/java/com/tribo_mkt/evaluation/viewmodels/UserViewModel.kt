package com.tribo_mkt.evaluation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.domain.User
import com.tribo_mkt.evaluation.respository.Repository
import kotlinx.coroutines.launch

enum class Status {
    LOADING, FINISHED
}

class UserViewModel(private val repo: Repository) : ViewModel() {

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status


    private var _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        _status.value = Status.LOADING
        viewModelScope.launch {
            _users.value = repo.getAllUsers().await()
            _status.value = Status.FINISHED
        }
    }

}