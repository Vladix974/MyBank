package com.example.mybank.screens.privatSreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.api.model.privat.PrivatCurrencyItem
import com.example.mybank.databinding.ItemOtherBinding
import kotlin.math.roundToInt

class PrivatAdapterOther : RecyclerView.Adapter<PrivatAdapterOther.PrivatHolder>() {
    private var list = emptyList<PrivatCurrencyItem>()

    inner class PrivatHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemOtherBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(bank: PrivatCurrencyItem) = with(binding) {
                tvCodOfCurrencyDetails.text =bank.ccy
            if(bank.ccy =="USD"){
                tvNameOfCurrencyDetails.text = "Долар США"}else{
                    tvNameOfCurrencyDetails.text = "Євро"
                }

            tvBuyDetails.text =
                (((bank.buy).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
            tvSellDetails.text =
                (((bank.sale).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
            tvCrossDetails.text =
                (((0.0).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_other, parent, false)
        return PrivatHolder(view)
    }

    override fun getItemCount(): Int {
        return minOf(list.size,1)
    }

    override fun onBindViewHolder(holder: PrivatHolder, position: Int) {
        holder.bind(list[position])

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addPrivatCurrencyDetails(newList: List<PrivatCurrencyItem>) {
        list = newList
        notifyDataSetChanged()
    }
}