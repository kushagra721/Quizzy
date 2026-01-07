package com.quizzy.`in`.userInterface.home

import com.quizzy.`in`.data.modal.HomeResponse

data class HomeUiState(
    val isLoading: Boolean = false,
    val response: HomeResponse? = null,
    val error: String? = null
)