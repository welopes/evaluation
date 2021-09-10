package com.tribo_mkt.evaluation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.domain.User
import com.tribo_mkt.evaluation.respository.Repository
import com.tribo_mkt.evaluation.util.Status
import kotlinx.coroutines.launch

class MainViewModel(private val repo: Repository) : ViewModel() {

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private var _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

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
                _error.value = t
            }
        }
    }

}