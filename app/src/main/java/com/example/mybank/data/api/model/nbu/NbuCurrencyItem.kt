package com.example.mybank.data.api.model.nbu

data class NbuCurrencyItem(
    val cc: String,
    val exchangedate: String,
    val r030: Int,
    val rate: Double,
    val txt: String
)