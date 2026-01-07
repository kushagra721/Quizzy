package com.quizzy.`in`.data.repository

import com.quizzy.`in`.data.modal.HomeResponse
import com.quizzy.`in`.data.remote.HomeApi

class HomeRepository(
    private val api: HomeApi
) {
    suspend fun getHomeData(): HomeResponse {
        return api.getHomeData()
    }
}