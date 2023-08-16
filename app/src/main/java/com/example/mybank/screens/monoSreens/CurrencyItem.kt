package com.example.mybank.screens.monoSreens

import android.annotation.SuppressLint
import java.util.Currency

class CurrencyItem {
    private lateinit var mCurrencyCod : String
    fun CurrencyItem (currencyCod: String){
        mCurrencyCod = currencyCod
    }
    fun getCurrencyCod(): String{
        return mCurrencyCod
    }
}