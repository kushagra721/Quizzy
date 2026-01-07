package com.quizzy.`in`.navigation


sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Notifications : Screen("notifications")
}