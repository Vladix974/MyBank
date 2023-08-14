package com.example.mybank.data.api.privatApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PrivatRetrofitInstance {
    private val retrofitPrivat by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.privatbank.ua/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiPrivat: PrivatApiInstance by lazy {
        retrofitPrivat.create(PrivatApiInstance::class.java)
    }
}