package com.quizzy.`in`.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await


class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    suspend fun login(
        schoolId: String,
        studentId: String
    ): Result<Unit> {

        val email = "$schoolId@quizzy.com"
        val password = studentId

        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}