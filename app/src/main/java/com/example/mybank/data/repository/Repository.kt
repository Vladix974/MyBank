package com.example.mybank.data.repository

import com.example.mybank.data.api.RetrofitInstance
import com.example.mybank.data.api.model.bezgot.Bezgotivka
import com.example.mybank.data.api.model.got.Gotivka
import retrofit2.Response


class Repository {
    suspend fun getGotivka(): Response<Gotivka>{
       return RetrofitInstance.api.getGotMoney()
    }
    suspend fun getBezgotivka(): Response<Bezgotivka>{
        return RetrofitInstance.api.getBezgotMoney()
    }
}