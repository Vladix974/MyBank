package com.example.mybank.screens.nbuScreens

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybank.R
import com.example.mybank.databinding.FragmentNbuBinding
import com.example.mybank.screens.monoSreens.CurrencyConvertor
import com.example.mybank.screens.monoSreens.MonoViewModel
import java.text.NumberFormat
import kotlin.math.roundToInt

class NbuFragment: Fragment(R.layout.fragment_nbu) {
    private lateinit var binding: FragmentNbuBinding
    private var adapterNbu = NbuAdapter()
    private var adapterNbuOther = NbuAdapterOther()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNbuBinding.bind(view)

        binding.recyclerViewPopular.adapter = adapterNbu
        binding.recycleViewOther1.adapter = adapterNbuOther

        val viewModel = ViewModelProvider(this)[NbuViewModel::class.java]

        viewModel.getNbu()

        viewModel.myCurrencyNbu.observe(viewLifecycleOwner){list->
            list.body()?.let { adapterNbu.addNbuCurrency(it) }
        }
        setupCurrencySpinner()
    }
    private fun setupCurrencySpinner() {
        val currencySpinner = binding.currencySpinner

        val viewModel = ViewModelProvider(this)[NbuViewModel::class.java]

        viewModel.myCurrencyNbu.observe(viewLifecycleOwner) { list ->
            // Отримати унікальні коди валют зі списку банків
            val currencyCodes = list.body()?.mapNotNull { bank ->
                bank.cc
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
                    val selectedCurrencyCode = currencyCodes[position]
                    binding.editTextCurrency1.clearFocus()
                    binding.editTextCurrency2.clearFocus()
                    binding.tvInformation2.text = "Ви ще нічого не перевели ☺"
                    val filteredBanks = list.body()?.filter { bank ->
                        bank.cc == selectedCurrencyCode
                    } ?: emptyList()

                    adapterNbuOther.addNbuCurrencyDetails(filteredBanks)

                    if (filteredBanks.isNotEmpty()) {
                        val selectedBank = filteredBanks[0]

                        binding.editTextCurrency1.setOnFocusChangeListener { _, hasFocus ->
                            if (hasFocus) {
                                binding.editTextCurrency1.text.clear()
                                binding.button.setOnClickListener {
                                    val editText1Text = binding.editTextCurrency1.text.toString()
                                    if (editText1Text.isNotEmpty()) {
                                        val editText1Value = editText1Text.toDouble()
                                            val result =
                                                (((editText1Value * selectedBank.rate).toFloat() * 100.0).roundToInt() / 100.0)
                                            binding.editTextCurrency2.setText(result.toString())


                                        val editText1Text1 =
                                            binding.editTextCurrency1.text.toString()
                                                .replace(",", "")
                                        val editText2Text2 =
                                            binding.editTextCurrency2.text.toString()
                                                .replace(",", "")
                                        val formattedEditText1Value =
                                            NumberFormat.getNumberInstance()
                                                .format(editText1Text1.toDouble())
                                        val formattedEditText2Value =
                                            NumberFormat.getNumberInstance()
                                                .format(editText2Text2.toDouble())

                                        binding.tvInformation2.text =
                                            "$formattedEditText1Value $selectedCurrencyCode = $formattedEditText2Value UAH"
                                    } else {
                                        Toast.makeText(
                                            requireContext(),
                                            "Будь ласка, введіть суму, яку потрібно перевести",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            }
                        }


                        binding.editTextCurrency2.setOnFocusChangeListener { _, hasFocus ->
                            if (hasFocus) {
                                binding.editTextCurrency2.text.clear()
                                binding.button.setOnClickListener {
                                    val editText2Text = binding.editTextCurrency2.text.toString()
                                    if (editText2Text.isNotEmpty()) {
                                        val editText2Value = editText2Text.toDouble()
                                            val result =
                                                (((editText2Value / selectedBank.rate).toFloat() * 100.0).roundToInt() / 100.0)
                                            binding.editTextCurrency1.setText(result.toString())

                                        val editText1Text1 =
                                            binding.editTextCurrency1.text.toString()
                                                .replace(",", "")
                                        val editText2Text2 =
                                            binding.editTextCurrency2.text.toString()
                                                .replace(",", "")
                                        val formattedEditText1Value =
                                            NumberFormat.getNumberInstance()
                                                .format(editText1Text1.toDouble())
                                        val formattedEditText2Value =
                                            NumberFormat.getNumberInstance()
                                                .format(editText2Text2.toDouble())

                                        binding.tvInformation2.text =
                                            "$formattedEditText1Value $selectedCurrencyCode = $formattedEditText2Value UAH"
                                    } else {
                                        Toast.makeText(
                                            requireContext(),
                                            "Будь ласка, введіть суму, яку потрібно перевести",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                            }
                        }

                        binding.tvCodA.text = selectedCurrencyCode
                            binding.tvInformation.text =
                                "1 $selectedCurrencyCode = " + (((selectedBank.rate).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Нічого не вибрано
                }
            }
        }
    }
}