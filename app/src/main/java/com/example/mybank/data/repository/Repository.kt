package com.example.mybank.data.repository

import com.example.mybank.data.api.monoApi.RetrofitInstance
import com.example.mybank.data.api.model.mono.MonoCurrency
import com.example.mybank.data.api.model.nbu.NbuCurrency
import com.example.mybank.data.api.model.privat.PrivatCurrency
import com.example.mybank.data.api.nbuApi.NbuRetrofitInstance
import com.example.mybank.data.api.privatApi.PrivatRetrofitInstance
import retrofit2.Response


class Repository {
    suspend fun getMonoCurrency(): Response<MonoCurrency>{
       return RetrofitInstance.apiMono.getGotCurrencyMono()
    }
    suspend fun getNbuCurrency(): Response<NbuCurrency> {
        return NbuRetrofitInstance.apiNbu.getCurrencyNbu()
    }
    suspend fun getPrivatCurrency():Response<PrivatCurrency>{
        return PrivatRetrofitInstance.apiPrivat.getCurrencyPrivat()
    }
}