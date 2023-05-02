package com.example.mybank.screens.euro

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mybank.R
import com.example.mybank.databinding.FragmentEuroBinding
import com.example.mybank.data.api.model.bezgot.Bezgotivka
import com.example.mybank.data.api.model.bezgot.BezgotivkaItem
import com.example.mybank.data.api.model.got.Gotivka
import com.example.mybank.data.api.model.got.GotivkaItem
import com.example.mybank.screens.dollars.DollarsViewModel
import kotlin.math.roundToInt

class EuroFragment : Fragment(R.layout.fragment_euro) {
    private lateinit var binding : FragmentEuroBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentEuroBinding.bind(view)
        val url = "https://upload.wikimedia.org/wikipedia/commons/7/7c/Animated-Flag-Eu.gif"
        var listEurosGot : List<GotivkaItem>
        var listEurosBezGot : List<BezgotivkaItem>
        Glide.with(this)
            //.asBitmap()
            .load(url)
            .into(binding.img)

        val viewModel = ViewModelProvider(this).get(EuroViewModel::class.java)
        viewModel.getEuro()

        viewModel.myGotivka.observe(viewLifecycleOwner) { list ->
            listEurosGot = list.body()!!
            bindGot( listEurosGot)
        }
        viewModel.myBezgotivka.observe(viewLifecycleOwner
        ) { list ->
            listEurosBezGot = list.body()!!
            bindBezgot(listEurosBezGot)
        }
        setBezGotBuy()
        setBezGotSale()
        setGotSale()
        setGotBuy()

    }
    private fun bindGot(listEurosGot: List<GotivkaItem>){
        binding.apply {
            //готівковий курс
            buyGot.text = (((listEurosGot[0].buy).toFloat()*100.0).roundToInt() /100.0).toString()
            saleGot.text = (((listEurosGot[0].sale).toFloat()*100.0).roundToInt() /100.0).toString()
        }
    }
    private fun bindBezgot(listEurosBezGot: List<BezgotivkaItem> ){
        binding.apply { name.text = listEurosBezGot[0].ccy
            //безготівковий курс
            buyBez.text = (((listEurosBezGot[0].buy).toFloat()*100.0).roundToInt() /100.0).toString()
            saleBez.text = (((listEurosBezGot[0].sale).toFloat()*100.0).roundToInt() /100.0).toString()  }
    }
    private fun setBezGotSale() {
        binding.apply {
            editEurosToUAH.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    bezGotSale()
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun bezGotSale() {
        // dollarsToUAH
        binding.apply {
            val edit: Float
            if (editEurosToUAH.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editEurosToUAH.text.toString()).toFloat()
            }
            val course: Float = (saleBez.text.toString()).toFloat()
            eurotoUa.text = (((edit * course)*100.0).roundToInt()/100.0).toString()
        }
    }
    private fun setBezGotBuy() {
        binding.apply {
            editEurosToUAH2.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    bezGotBuy()
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }
    private fun bezGotBuy() {
        // UAHTodollars
        binding.apply {
            val edit: Float
            if (editEurosToUAH2.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editEurosToUAH2.text.toString()).toFloat()
            }
            val course: Float = (buyBez.text.toString()).toFloat()
            uaToEuro.text = (((edit / course)*100.0).roundToInt()/100.0).toString()
        }
    }

    private fun setGotSale() {
        binding.apply {
            editEurosToUAHGot.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    gotSale()
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }

    private fun gotSale() {
        // dollarsToUAH
        binding.apply {
            val edit: Float
            if (editEurosToUAHGot.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editEurosToUAHGot.text.toString()).toFloat()
            }
            val course: Float = (saleGot.text.toString()).toFloat()
            eurotoUaGot.text = (((edit * course)*100.0).roundToInt()/100.0).toString()
        }
    }
    private fun setGotBuy() {
        binding.apply {
            editEurosToUAH2Got.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    gotBuy()
                }
                override fun afterTextChanged(s: Editable?) {
                }
            })
        }
    }
    private fun gotBuy() {
        // UAHTodollars
        binding.apply {
            val edit: Float
            if (editEurosToUAH2Got.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editEurosToUAH2Got.text.toString()).toFloat()
            }
            val course: Float = (buyGot.text.toString()).toFloat()
            uaToEuroGot.text = (((edit / course)*100.0).roundToInt()/100.0).toString()
        }
    }

}