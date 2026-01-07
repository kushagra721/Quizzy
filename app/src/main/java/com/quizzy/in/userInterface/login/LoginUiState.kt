package com.quizzy.`in`.userInterface.login

data class LoginUiState(
    val username: String = "SGGP782001",
    val password: String = "SG211",
    val isLoading: Boolean = false,
    val error: String? = null
)