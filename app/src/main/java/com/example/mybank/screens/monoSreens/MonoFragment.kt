package com.example.mybank.screens.monoSreens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.mybank.R
import com.example.mybank.data.api.model.mono.MonoCurrencyItem
import com.example.mybank.databinding.FragmentMonoBinding


open class MonoFragment : Fragment(R.layout.fragment_mono) {
    private lateinit var binding: FragmentMonoBinding
    private var adapterMono = MonoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonoBinding.bind(view)

        binding.recyclerViewPopular.adapter = adapterMono

        val viewModel = ViewModelProvider(this)[MonoViewModel::class.java]
        viewModel.getMono()

        viewModel.myCurrencyMono.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapterMono.addMonoCurrency(it) }
        }

        setupCurrencySpinner() // Виклик функції налаштування спіннера
    }

    private fun setupCurrencySpinner() {
        val currencySpinner = binding.currencySpinner
        var currencyConvertor: CurrencyConvertor

        val viewModel = ViewModelProvider(this)[MonoViewModel::class.java]

        viewModel.myCurrencyMono.observe(viewLifecycleOwner) { list ->
            // Отримати унікальні коди валют зі списку банків
            val currencyCodes = list.body()?.mapNotNull { bank ->
                currencyConvertor = CurrencyConvertor(bank.currencyCodeA.toString())
                if (currencyConvertor.currencyDetails?.first != null) {
                    currencyConvertor.currencyDetails?.first
                } else {
                    null
                }
            }?.distinct() ?: emptyList()


            // Створити адаптер для спіннера зі списком унікальних кодів валют
            val currencyAdapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, currencyCodes)
            currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            currencySpinner.adapter = currencyAdapter

            currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                @SuppressLint("SetTextI18n")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // Отримати обраний код валюти
                    val selectedCurrencyCode = currencyCodes[position]
                    binding.tvCodOfCurrencyDetails.text = selectedCurrencyCode

                    // Фільтрувати список банків за обраним кодом валюти
                    val filteredBanks = list.body()?.filter { bank ->
                        currencyConvertor = CurrencyConvertor(bank.currencyCodeA.toString())
                        currencyConvertor.currencyDetails?.first == selectedCurrencyCode
                    } ?: emptyList()

                    binding.tvNameOfCurrencyDetails.text = CurrencyConvertor(filteredBanks[position].currencyCodeA.toString()).currencyDetails?.second
                    binding.tvBuyDetails.text = filteredBanks[position].rateBuy.toString() + " грн"
                    binding.tvSellDetails.text = filteredBanks[position].rateSell.toString() + " грн"
                    // Оновити адаптер для списку банків
                    //  adapterMono.addMonoCurrency(filteredBanks)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Нічого не вибрано
                }
            }
        }
    }
}


//class MonoFragment : Fragment(R.layout.fragment_mono) {
//    private lateinit var binding : FragmentMonoBinding
//    private var adapterMono = MonoAdapter()
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentMonoBinding.bind(view)
//
//        binding.recyclerViewPopular.adapter = adapterMono
//
//        val viewModel = ViewModelProvider(this)[MonoViewModel::class.java]
//        viewModel.getMono()
//
//        viewModel.myCurrencyMono.observe(viewLifecycleOwner) { list ->
//            list.body()?.let { adapterMono.addMonoCurrency(it) }
//        }
//    }
//}