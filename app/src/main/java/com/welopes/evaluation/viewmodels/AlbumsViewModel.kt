package com.welopes.evaluation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welopes.evaluation.domain.Album
import com.welopes.evaluation.respository.Repository
import com.welopes.evaluation.util.Status
import kotlinx.coroutines.launch

class AlbumsViewModel(private val repo: Repository) : ViewModel() {

    private var _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private var _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>>
        get() = _albums

    fun getAlbumsByUserId(userId: Int) {
        _status.value = Status.LOADING
        viewModelScope.launch {
            try {
                _albums.value = repo.getAlbumsByUserId(userId)
                _status.value = Status.SUCCESS
            } catch (t: Throwable) {
                _albums.value = emptyList()
                _status.value = Status.ERROR
            }
        }
    }
}