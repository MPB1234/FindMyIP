package com.photon.findmyip.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.photon.findmyip.model.Location
import com.photon.findmyip.repository.FindIpRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindIpViewModel @Inject constructor(private val repo: FindIpRepository) : ViewModel() {

    private var default: UiState = UiState.Loading
    private val _uiState = mutableStateOf(default)
    val state: State<UiState> = _uiState

    fun fetchData() {

        _uiState.value = UiState.Loading
        viewModelScope.launch {

            val result = repo.fetchData("115.96.18.65", "json")
            if (result.isSuccessful) {
                _uiState.value = UiState.Success(result.body())
            } else {
                _uiState.value = UiState.Error(error = "Error in fetching data!!!!")
            }
        }
    }


    sealed class UiState {
        object Loading : UiState()

        data class Error(val error: String) : UiState()

        data class Success(val data: Location?) : UiState()

    }
}
