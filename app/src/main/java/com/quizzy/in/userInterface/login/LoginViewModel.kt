package com.quizzy.`in`.userInterface.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.update { it.copy(username = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onLoginClick(onSuccess: () -> Unit) {
        if (_uiState.value.username.isNotBlank() &&
            _uiState.value.password.isNotBlank()
        ) {
            onSuccess()
        } else {
            _uiState.update { it.copy(error = "Invalid credentials") }
        }
    }
}