package com.example.mybank.data.api.nbuApi

import com.example.mybank.data.api.model.nbu.NbuCurrency
import retrofit2.Response
import retrofit2.http.GET

interface NbuApiService {

    @GET("NBUStatService/v1/statdirectory/exchange?json")
    suspend fun getCurrencyNbu():Response<NbuCurrency>
    }