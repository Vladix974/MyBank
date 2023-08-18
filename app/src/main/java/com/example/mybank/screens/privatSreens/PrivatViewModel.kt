package com.example.mybank.screens.privatSreens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybank.data.api.model.privat.PrivatCurrency
import com.example.mybank.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class PrivatViewModel :ViewModel() {

  private val repo = Repository()

   val myCurrencyPrivatBezGot : MutableLiveData<Response<PrivatCurrency>> = MutableLiveData()
   val myCurrencyPrivatGot : MutableLiveData<Response<PrivatCurrency>> = MutableLiveData()

    fun getPrivat(){
        viewModelScope.launch {
            myCurrencyPrivatBezGot.value = repo.getPrivatCurrencyGot()
            myCurrencyPrivatGot.value = repo.getPrivatCurrencyBezGot()
        }
    }
}