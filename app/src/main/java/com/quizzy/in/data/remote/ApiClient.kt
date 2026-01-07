package com.quizzy.`in`.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    private const val BASE_URL =
        "https://firebasestorage.googleapis.com/v0/b/user-contacts-ade83.appspot.com/o/"

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val api: HomeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // ✅ ends with /
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeApi::class.java)
    }
}