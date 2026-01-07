package com.quizzy.`in`.userInterface.notifications

import androidx.lifecycle.ViewModel
import com.quizzy.`in`.userInterface.login.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotificationViewModal : ViewModel() {

    private val _uiState = MutableStateFlow(NotificationUiState())
    val uiState = _uiState.asStateFlow()


}