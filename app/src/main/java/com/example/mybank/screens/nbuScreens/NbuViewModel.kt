package com.example.mybank.screens.nbuScreens

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybank.data.api.model.nbu.NbuCurrency
import com.example.mybank.data.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class NbuViewModel: ViewModel() {
    private val repo = Repository()

    val myCurrencyNbu: MutableLiveData<Response<NbuCurrency>> = MutableLiveData()

    fun getNbu(){
        viewModelScope.launch {
            myCurrencyNbu.value = repo.getNbuCurrency()
        }
    }
}