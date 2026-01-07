package com.quizzy.`in`.userInterface.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quizzy.`in`.data.remote.ApiClient
import com.quizzy.`in`.data.repository.HomeRepository
import com.quizzy.`in`.userInterface.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repository = HomeRepository(ApiClient.api)

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchHomeData()
    }

    private fun fetchHomeData() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true) }

            try {
                val response = repository.getHomeData()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        response = response
                    )
                }

            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message
                    )
                }
            }
        }
    }
}