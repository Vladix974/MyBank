package com.example.mybank.screens.monoSreens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybank.data.api.model.mono.MonoCurrency
import com.example.mybank.data.api.model.mono.MonoCurrencyItem
import com.example.mybank.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MonoViewModel :ViewModel(){
    private val repo = Repository()

    val myCurrencyMono: MutableLiveData<Response<MonoCurrency>> = MutableLiveData()

    fun getMono(){
        viewModelScope.launch {
           myCurrencyMono.value = repo.getMonoCurrency()
        }
    }
}