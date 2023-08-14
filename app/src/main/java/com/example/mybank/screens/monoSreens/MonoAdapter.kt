package com.example.mybank.screens.monoSreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.api.model.mono.MonoCurrencyItem

import com.example.mybank.databinding.ItemPopularBinding
import kotlin.math.roundToInt

class MonoAdapter : RecyclerView.Adapter<MonoAdapter.MonoHolder>() {
    private var list = emptyList<MonoCurrencyItem>()

    lateinit var currencyConvertor: CurrencyConvertor


    inner class MonoHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemPopularBinding.bind(item)


        @SuppressLint("SetTextI18n")
        fun bind(bank: MonoCurrencyItem) = with(binding){
            currencyConvertor= CurrencyConvertor(bank.currencyCodeA.toString())
            if(currencyConvertor.currencyDetails?.first !=null && currencyConvertor.currencyDetails?.second!=null){
            tvCodOfCurrency.text = currencyConvertor.currencyDetails?.first
            }else{
                tvCodOfCurrency.text = "No information"
            }
            tvBuy.text= (((bank.rateBuy).toFloat()*100.0).roundToInt() /100.0).toString() + " грн"
            tvSell.text= (((bank.rateSell).toFloat()*100.0).roundToInt() /100.0).toString() + " грн"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular,parent,false)
        return MonoHolder(view)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: MonoHolder, position: Int) {
        holder.bind(list[position])
    }
    @SuppressLint("NotifyDataSetChanged")
    fun addMonoCurrency(newList: List<MonoCurrencyItem>){
        list = newList
        notifyDataSetChanged()
    }
}