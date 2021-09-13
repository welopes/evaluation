package com.welopes.evaluation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welopes.evaluation.domain.User
import com.welopes.evaluation.respository.Repository
import com.welopes.evaluation.util.Status
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repository) : ViewModel() {

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
            try {
                _users.value = repo.getAllUsers()
                _status.value = Status.SUCCESS
            } catch (t: Throwable) {
                _users.value = emptyList()
                _status.value = Status.ERROR
            }
        }
    }

}