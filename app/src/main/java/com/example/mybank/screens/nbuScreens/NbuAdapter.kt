package com.example.mybank.screens.nbuScreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.api.model.nbu.NbuCurrencyItem
import kotlin.math.roundToInt

class NbuAdapter() :RecyclerView.Adapter<NbuAdapter.NbuHolder>(){

    private var list = emptyList<NbuCurrencyItem>()

    inner class NbuHolder (item: View): RecyclerView.ViewHolder(item){
        //val binding = ItemBinding.bind(item)

//        fun bind (bank: NbuCurrencyItem) = with(binding){
//            tvCodOfCurrency.text = bank.cc
//            tvNameOfCurrency.text = bank.txt
//            tvBuy.text = (((bank.rate).toFloat()*100.0).roundToInt() /100.0).toString() + " грн"
//
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other,parent,false)
        return NbuHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: NbuHolder, position: Int) {
//      holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNbuCurrency(newList: List<NbuCurrencyItem>){
        list = newList
        notifyDataSetChanged()
    }
}