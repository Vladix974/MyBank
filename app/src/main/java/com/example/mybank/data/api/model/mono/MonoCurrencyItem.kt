package com.example.mybank.data.api.model.mono

data class MonoCurrencyItem(
    val currencyCodeA: Int,
    val currencyCodeB: Int,
    val date: Int,
    val rateBuy: Double,
    val rateCross: Double,
    val rateSell: Double,
    val currencyDetails: String?
)