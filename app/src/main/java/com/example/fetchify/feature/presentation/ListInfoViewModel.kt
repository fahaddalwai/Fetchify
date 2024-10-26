package com.example.fetchify.feature.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetchify.core.util.Resource
import com.example.fetchify.feature.domain.use_case.GetListInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListInfoViewModel @Inject constructor(
    private val getListInfo: GetListInfo
) : ViewModel() {

    private val _state = MutableLiveData<ListInfoState>()
    val state: LiveData<ListInfoState> = _state

    private val _showProgressBar = MutableLiveData<Boolean>()
    val showProgressBar: LiveData<Boolean> = _showProgressBar

    private var fetchJob: Job? = null

    init {
        _state.value = ListInfoState()
    }

    fun fetchListInfo() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            _showProgressBar.value = true // Show progress bar when starting to fetch
            getListInfo() // Directly fetch list info by listId
                .onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = ListInfoState(
                                listInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            Log.i("ListInfoViewModel", "Success: ${_state.value}")
                            Log.i("The values", "Success: ${_state.value}")
                            _showProgressBar.value = false
                        }
                        is Resource.Error -> {
                            _state.value = ListInfoState(
                                listInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            Log.i("ListInfoViewModel", "Error: ${_state.value}")
                            _showProgressBar.value = false
                        }
                        is Resource.Loading -> {
                            _state.value = ListInfoState(
                                listInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                            Log.i("ListInfoViewModel", "Loading: ${_state.value}")
                            _showProgressBar.value = true
                        }
                    }
                }.launchIn(this)
        }
    }
}