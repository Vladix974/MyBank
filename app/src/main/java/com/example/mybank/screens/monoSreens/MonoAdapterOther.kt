package com.example.mybank.screens.monoSreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.api.model.mono.MonoCurrencyItem
import com.example.mybank.databinding.ItemOtherBinding

import com.example.mybank.databinding.ItemPopularBinding
import kotlin.math.min
import kotlin.math.roundToInt

class MonoAdapterOther : RecyclerView.Adapter<MonoAdapterOther.MonoHolder>() {
    private var list = emptyList<MonoCurrencyItem>()

    lateinit var currencyConvertor: CurrencyConvertor
    inner class MonoHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemOtherBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(bank: MonoCurrencyItem) = with(binding) {
            currencyConvertor = CurrencyConvertor(bank.currencyCodeA.toString())
            if (currencyConvertor.currencyDetails?.first != null && currencyConvertor.currencyDetails?.second != null) {
                tvCodOfCurrencyDetails.text = currencyConvertor.currencyDetails?.first
                tvNameOfCurrencyDetails.text = currencyConvertor.currencyDetails?.second
            } else {
                tvCodOfCurrencyDetails.text = "No information"
                tvNameOfCurrencyDetails.text = "No information"
            }
            tvBuyDetails.text =
                (((bank.rateBuy).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
            tvSellDetails.text =
                (((bank.rateSell).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
            tvCrossDetails.text =
                (((bank.rateCross).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other, parent, false)
        return MonoHolder(view)
    }

    override fun getItemCount(): Int {
        return minOf(list.size,1)
    }

    override fun onBindViewHolder(holder: MonoHolder, position: Int) {
        holder.bind(list[position])

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMonoCurrencyDetails(newList: List<MonoCurrencyItem>) {
        list = newList
        notifyDataSetChanged()
    }
}