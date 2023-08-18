package com.example.mybank.screens.nbuScreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.api.model.nbu.NbuCurrencyItem
import com.example.mybank.databinding.ItemOtherNbuBinding
import kotlin.math.roundToInt

class NbuAdapterOther : RecyclerView.Adapter<NbuAdapterOther.NbuHolder>() {
    private var list = emptyList<NbuCurrencyItem>()
    inner class NbuHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemOtherNbuBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(bank: NbuCurrencyItem) = with(binding) {
            tvCodOfCurrencyDetails.text = bank.cc
            tvNameOfCurrencyDetails.text = bank.txt
            tvDateDetails.text = bank.exchangedate
            tvBuyDetails.text = (((bank.rate).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other_nbu, parent, false)
        return NbuHolder(view)
    }

    override fun getItemCount(): Int {
        return minOf(list.size,1)
    }

    override fun onBindViewHolder(holder: NbuHolder, position: Int) {
        holder.bind(list[position])

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNbuCurrencyDetails(newList: List<NbuCurrencyItem>) {
        list = newList
        notifyDataSetChanged()
    }
}