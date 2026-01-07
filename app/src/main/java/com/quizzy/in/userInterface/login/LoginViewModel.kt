package com.quizzy.`in`.userInterface.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quizzy.`in`.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val repository = AuthRepository()
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onUsernameChange(value: String) {
        _uiState.update { it.copy(username = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onLoginClick(
        schoolId: String,
        studentId: String,
        onSuccess: () -> Unit
    ) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val result = repository.login(schoolId, studentId)

            result
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                    onSuccess()
                }
                .onFailure {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = "Invalid credentials"
                        )
                    }
                }
        }
    }

 /*   fun onLoginClick(onSuccess: () -> Unit) {
        if (_uiState.value.username.isNotBlank() &&
            _uiState.value.password.isNotBlank()
        ) {
            onSuccess()
        } else {
            _uiState.update { it.copy(error = "Invalid credentials") }
        }
    }*/
}