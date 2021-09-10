package com.tribo_mkt.evaluation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tribo_mkt.evaluation.domain.Album
import com.tribo_mkt.evaluation.respository.Repository
import com.tribo_mkt.evaluation.util.Status
import kotlinx.coroutines.launch

class AlbumsViewModel(private val repo: Repository) : ViewModel() {

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private var _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    private var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    fun getAlbumsByUserId(userId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch {
            try {
                _albums.value = repo.getAlbumsByUserId(userId)
                _status.value = Status.SUCCESS
            } catch (t: Throwable) {
                _albums.value = emptyList()
                _error.value = t
            }
        }
    }
}