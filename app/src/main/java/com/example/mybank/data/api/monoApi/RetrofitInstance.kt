package com.example.mybank.data.api.monoApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val retrofitMono by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.monobank.ua/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiMono: ApiService by lazy {
        retrofitMono.create(ApiService::class.java)
    }
}