package com.example.mybank.data.api.monoApi

import com.example.mybank.data.api.model.mono.MonoCurrency
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("bank/currency")
    suspend fun getGotCurrencyMono():Response<MonoCurrency>
}