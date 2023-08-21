package com.example.mybank.screens.monoSreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybank.R
import com.example.mybank.data.api.model.mono.MonoCurrencyItem

import com.example.mybank.databinding.ItemPopularBinding
import kotlin.math.min
import kotlin.math.roundToInt

class MonoAdapter : RecyclerView.Adapter<MonoAdapter.MonoHolder>() {
    private var list = emptyList<MonoCurrencyItem>()

    lateinit var currencyConvertor: CurrencyConvertor
    inner class MonoHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemPopularBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(bank: MonoCurrencyItem) = with(binding) {
            if(bank.currencyCodeA == 840 ||(bank.currencyCodeA == 978 && bank.currencyCodeB == 980)||bank.currencyCodeA == 985){
            currencyConvertor = CurrencyConvertor(bank.currencyCodeA.toString())

            if (currencyConvertor.currencyDetails?.first != null && currencyConvertor.currencyDetails?.second != null) {
                tvCodOfCurrency.text = currencyConvertor.currencyDetails?.first
            } else {
                tvCodOfCurrency.text = "No information"
            }
                if(bank.rateBuy!=0.0 || bank.rateSell!=0.0) {
                    tvBuy.text =
                        (((bank.rateBuy).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
                    tvSell.text =
                        (((bank.rateSell).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
                }else{
                    tvBuy.text =
                        (((bank.rateCross).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
                    tvSell.text =
                        (((bank.rateCross).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
                }
            Glide.with(binding.root)
                .load(currencyConvertor.currencyDetails?.third)
                .centerCrop()
                .into(imgOfCountry)
        }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular, parent, false)
        return MonoHolder(view)
    }

    override fun getItemCount(): Int {
        return list.count{it.currencyCodeA == 840 ||(it.currencyCodeA == 978 && it.currencyCodeB == 980)||it.currencyCodeA == 985}
    }

    override fun onBindViewHolder(holder: MonoHolder, position: Int) {
           val filteredItems = list.filter { it.currencyCodeA == 840 ||(it.currencyCodeA == 978 && it.currencyCodeB == 980)||it.currencyCodeA == 985 }
            holder.bind(filteredItems[position])

    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMonoCurrency(newList: List<MonoCurrencyItem>) {
        list = newList
        notifyDataSetChanged()
    }
}