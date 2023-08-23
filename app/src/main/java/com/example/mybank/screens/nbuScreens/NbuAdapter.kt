package com.example.mybank.screens.nbuScreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybank.R
import com.example.mybank.data.api.model.nbu.NbuCurrencyItem
import com.example.mybank.databinding.ItemPopularNbuBinding
import com.example.mybank.screens.monoSreens.CurrencyConvertor
import kotlin.math.roundToInt

class NbuAdapter : RecyclerView.Adapter<NbuAdapter.NbuHolder>() {

    private var list = emptyList<NbuCurrencyItem>()
    lateinit var currencyConvertor: CurrencyConvertor
    inner class NbuHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemPopularNbuBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(bank: NbuCurrencyItem) = with(binding) {
            if (bank.cc == "USD" || bank.cc == "EUR" || bank.cc =="PLN") {
                currencyConvertor = CurrencyConvertor(bank.r030.toString())

                Glide.with(binding.root)
                    .load(currencyConvertor.currencyDetails?.third)
                    .centerCrop()
                    .into(imgOfCountry)

                tvCodOfCurrency.text = bank.cc
                tvRate.text = (((bank.rate).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NbuHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular_nbu, parent, false)
        return NbuHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count { it.cc == "USD" || it.cc == "EUR" || it.cc =="PLN" }
    }

    override fun onBindViewHolder(holder: NbuHolder, position: Int) {
        val filteredItems = list.filter { it.cc == "USD" || it.cc == "EUR" || it.cc == "PLN" }
        holder.bind(filteredItems[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNbuCurrency(newList: List<NbuCurrencyItem>) {
        list = newList
        notifyDataSetChanged()
    }
}
