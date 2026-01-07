package com.quizzy.`in`.data.remote

import com.quizzy.`in`.data.modal.HomeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("student_dashboard.json")
    suspend fun getHomeData(
        @Query("alt") alt: String = "media",
        @Query("token") token: String =
            "0091b4c2-2ee2-4326-99cd-96d5312b34bd"
    ): HomeResponse
}