package com.example.mybank.screens.dollars

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mybank.R
import com.example.mybank.databinding.FragmentDollarsBinding
import com.example.mybank.data.api.model.bezgot.BezgotivkaItem
import com.example.mybank.data.api.model.got.GotivkaItem
import kotlin.math.roundToInt

class DollarsFragment : Fragment(R.layout.fragment_dollars) {
    lateinit var binding: FragmentDollarsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDollarsBinding.bind(view)
        val url = "https://usagif.com/wp-content/uploads/gifs/flag-america-usa-35.gif"

        var listDollarsGot: List<GotivkaItem>
        var listDollarsBezGot: List<BezgotivkaItem>

        Glide.with(this)
            //.asBitmap()
            .load(url)
            .into(binding.img)

        val viewModel = ViewModelProvider(this).get(DollarsViewModel::class.java)
        viewModel.getDollars()

        viewModel.myGotivka.observe(viewLifecycleOwner) { list ->
            listDollarsGot = list.body()!!
            bindGot(listDollarsGot)
        }
        viewModel.myBezgotivka.observe(
            viewLifecycleOwner
        ) { list ->
            listDollarsBezGot = list.body()!!
            bindBezgot(listDollarsBezGot)
        }
        // Переведення курсів валют
        setBezGotBuy()
        setBezGotSale()
        setGotSale()
        setGotBuy()


    }

    private fun bindGot(listDollarsGot: List<GotivkaItem>) {
        binding.apply {
            //готівковий курс
            buyGot.text = (((listDollarsGot[1].buy).toFloat()*100.0).roundToInt() /100.0).toString()
            saleGot.text = (((listDollarsGot[1].sale).toFloat()*100.0).roundToInt() /100.0).toString()
        }
    }

    private fun bindBezgot(listDollarsBezGot: List<BezgotivkaItem>) {
        binding.apply {
            name.text = listDollarsBezGot[1].ccy
            //безготівковий курс

            buyBez.text = (((listDollarsBezGot[1].buy).toFloat()*100.0).roundToInt() /100.0).toString()
            saleBez.text = (((listDollarsBezGot[1].sale).toFloat()*100.0).roundToInt() /100.0).toString()
        }
    }

    private fun setBezGotSale() {
        binding.apply {
            editDollarsToUAH.addTextChangedListener(object : TextWatcher {
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
            if (editDollarsToUAH.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editDollarsToUAH.text.toString()).toFloat()
            }
            val course: Float = (saleBez.text.toString()).toFloat()
            dollartoUa.text = (((edit * course)*100.0).roundToInt()/100.0).toString()
        }
    }
    private fun setBezGotBuy() {
        binding.apply {
            editDollarsToUAH2.addTextChangedListener(object : TextWatcher {
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
            if (editDollarsToUAH2.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editDollarsToUAH2.text.toString()).toFloat()
            }
            val course: Float = (buyBez.text.toString()).toFloat()
            uaToDollar.text = (((edit / course)*100.0).roundToInt()/100.0).toString()
        }
    }

    private fun setGotSale() {
        binding.apply {
            editDollarsToUAHGot.addTextChangedListener(object : TextWatcher {
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
            if (editDollarsToUAHGot.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editDollarsToUAHGot.text.toString()).toFloat()
            }
            val course: Float = (saleGot.text.toString()).toFloat()
            dollartoUaGot.text = (((edit * course)*100.0).roundToInt()/100.0).toString()
        }
    }
    private fun setGotBuy() {
        binding.apply {
            editDollarsToUAH2Got.addTextChangedListener(object : TextWatcher {
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
            if (editDollarsToUAH2Got.text.isEmpty()) {
                edit = 0.0f
            } else {
                edit = (editDollarsToUAH2Got.text.toString()).toFloat()
            }
            val course: Float = (buyGot.text.toString()).toFloat()
            uaToDollarGot.text = (((edit / course)*100.0).roundToInt()/100.0).toString()
        }
    }

}
