package com.example.mybank.data.api

import com.example.mybank.data.api.model.bezgot.Bezgotivka
import com.example.mybank.data.api.model.got.Gotivka
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("p24api/pubinfo?exchange&json&coursid=11")
    suspend fun getBezgotMoney():Response<Bezgotivka>

    @GET("p24api/pubinfo?json&exchange&coursid=5")
    suspend fun getGotMoney():Response<Gotivka>
}