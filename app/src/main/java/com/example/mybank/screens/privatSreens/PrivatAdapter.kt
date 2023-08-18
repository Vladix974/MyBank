package com.example.mybank.screens.privatSreens

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybank.R
import com.example.mybank.data.api.model.privat.PrivatCurrencyItem
import com.example.mybank.databinding.ItemPopularBinding
import kotlin.math.roundToInt

class PrivatAdapter: RecyclerView.Adapter<PrivatAdapter.PrivatHolder>(){
    private var list = emptyList<PrivatCurrencyItem>()

    inner class PrivatHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = ItemPopularBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind (bank: PrivatCurrencyItem) = with(binding){
            tvCodOfCurrency.text =bank.ccy
            tvBuy.text = (((bank.buy).toFloat()*100.0).roundToInt() /100.0).toString() + " грн"
            tvSell.text = (((bank.sale).toFloat()*100.0).roundToInt() /100.0).toString() + " грн"
       }
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrivatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_popular,parent,false)
        return PrivatHolder(view)
    }

    override fun getItemCount(): Int {
        return minOf(list.size,2)
    }

    override fun onBindViewHolder(holder: PrivatHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDateSetChanged", "NotifyDataSetChanged")
    fun addCarrency(newList: List<PrivatCurrencyItem>){
        list = newList
        notifyDataSetChanged()
    }
}