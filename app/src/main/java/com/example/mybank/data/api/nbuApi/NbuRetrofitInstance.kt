package com.example.mybank.data.api.nbuApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NbuRetrofitInstance {

    private val retrofitNbu by lazy {
        Retrofit.Builder()
            .baseUrl("https://bank.gov.ua/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiNbu: NbuApiService by lazy {
        retrofitNbu.create(NbuApiService::class.java)
    }
}