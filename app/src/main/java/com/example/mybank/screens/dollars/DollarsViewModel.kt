package com.example.mybank.screens.dollars

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mybank.data.repository.Repository
import com.example.mybank.data.api.model.bezgot.Bezgotivka
import com.example.mybank.data.api.model.got.Gotivka
import kotlinx.coroutines.launch
import retrofit2.Response

class DollarsViewModel :ViewModel() {

  private var repo = Repository()
   val myGotivka : MutableLiveData<Response<Gotivka>> = MutableLiveData()
   val myBezgotivka : MutableLiveData<Response<Bezgotivka>> = MutableLiveData()

    fun getDollars(){
        viewModelScope.launch {
            myGotivka.value = repo.getGotivka()
            myBezgotivka.value = repo.getBezgotivka()
        }
    }
}