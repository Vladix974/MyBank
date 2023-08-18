package com.example.mybank.data.api.privatApi

import com.example.mybank.data.api.model.privat.PrivatCurrency
import retrofit2.Response
import retrofit2.http.GET

interface PrivatApiInstance {

    @GET("p24api/pubinfo?exchange&coursid=11")
    suspend fun getCurrencyPrivatBezGot():Response<PrivatCurrency>

    @GET("p24api/pubinfo?exchange&coursid=5")
    suspend fun getCurrencyPrivatGot():Response<PrivatCurrency>
}