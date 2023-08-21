package com.example.mybank.screens.monoSreens

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.SpannableString
import android.text.Spanned
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mybank.R
import com.example.mybank.data.api.model.mono.MonoCurrencyItem
import com.example.mybank.databinding.ItemOtherBinding

import com.example.mybank.databinding.ItemPopularBinding
import kotlin.math.min
import kotlin.math.roundToInt

class MonoAdapterOther: RecyclerView.Adapter<MonoAdapterOther.MonoHolder>() {
    private var list = emptyList<MonoCurrencyItem>()

    lateinit var currencyConvertor: CurrencyConvertor
    inner class MonoHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ItemOtherBinding.bind(item)

        @SuppressLint("SetTextI18n")
        fun bind(bank: MonoCurrencyItem) = with(binding) {

            currencyConvertor = CurrencyConvertor(bank.currencyCodeA.toString())
            if (currencyConvertor.currencyDetails?.first !="BYN") {
                Glide.with(binding.root)
                    .load(currencyConvertor.currencyDetails?.third)
                    .centerCrop()
                    .into(imgOfCountryDetails)
                tvBuyDetails.visibility = View.VISIBLE
                tvSellDetails.visibility =View.VISIBLE
                tvCrossDetails.visibility =View.VISIBLE
                buyDetails.visibility =View.VISIBLE
                sellDetails.visibility =View.VISIBLE
                crossDetails.visibility =View.VISIBLE
                tvNameOfCurrencyDetails.setTextColor(Color.BLACK)
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
            } else {
                tvCodOfCurrencyDetails.text = "Вибачте, але інформацію про цю валюту \n Ви можете переглянути ЛИШЕ за посиланням нижче "
                tvNameOfCurrencyDetails.setTextColor(Color.BLUE)
                tvBuyDetails.visibility = View.GONE
                tvSellDetails.visibility =View.GONE
                tvCrossDetails.visibility =View.GONE
                buyDetails.visibility =View.GONE
                sellDetails.visibility =View.GONE
                crossDetails.visibility =View.GONE

                val text = "Посилання на сайт"
                val spannableString = SpannableString(text)
                spannableString.setSpan(UnderlineSpan(), 0, text.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                tvNameOfCurrencyDetails.text = SpannableString(text)
            }

            // Отримуємо контекст з itemView
            val context = itemView.context

            // Перевіряємо, чи currencyCodeA дорівнює 933
            if (bank.currencyCodeA == 933) {
                // Створюємо інтент для відкриття посилання
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://drive.google.com/file/d/1sWqD8gf5NgANNs4PQES8Vrw4idLWIgMz/view?usp=sharing")

                // Встановлюємо обробник кліку для binding.root
                binding.root.setOnClickListener {
                    // Запускаємо інтент
                    context.startActivity(intent)
                }
            }
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