package com.example.mybank.screens.privatSreens

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mybank.R
import com.example.mybank.data.api.model.nbu.NbuCurrencyItem
import com.example.mybank.data.api.model.privat.PrivatCurrencyItem
import com.example.mybank.databinding.FragmentPrivatBinding
import com.example.mybank.screens.monoSreens.CurrencyConvertor
import com.example.mybank.screens.monoSreens.MonoViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import kotlin.math.roundToInt

class PrivatFragment : Fragment(R.layout.fragment_privat) {
    private lateinit var binding: FragmentPrivatBinding
    private var adapterPrivat = PrivatAdapter()
    private var adapterPrivatOther = PrivatAdapterOther()
    lateinit var rotationAnimator: ObjectAnimator
    private var editText1ChangingByCode = false
    private var editText2ChangingByCode = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrivatBinding.bind(view)
        binding.recyclerViewPopular.adapter = adapterPrivat
        binding.recycleViewOther1.adapter = adapterPrivatOther

        val viewModel = ViewModelProvider(this)[PrivatViewModel::class.java]
        viewModel.getPrivat()

        viewModel.myCurrencyPrivatBezGot.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapterPrivat.addCarrency(it) }
        }

        setupCurrencySpinner()

        rotationAnimator = ObjectAnimator.ofFloat(binding.button, "rotation", 0f, 180f)
        rotationAnimator.duration = 300 // тривалість анімації
    }

    private fun setupCurrencySpinner() {
        val currencySpinner = binding.currencySpinner
        val typeCurrencySpinner = binding.gotBezGotSpinner

        val viewModel = ViewModelProvider(this)[PrivatViewModel::class.java]

        val paymentTypes = listOf("Готівковий", "Безготівковий")

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, paymentTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        typeCurrencySpinner.adapter = adapter

        typeCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {

                if (paymentTypes[position] == "Готівковий") {

                    viewModel.myCurrencyPrivatGot.observe(viewLifecycleOwner) { list ->
                        // Отримати унікальні коди валют зі списку банків
                        val currencyCodes = list.body()?.mapNotNull { bank ->
                            bank.ccy
                        }?.distinct() ?: emptyList()

                        val currencyAdapter = ArrayAdapter(
                            requireContext(), android.R.layout.simple_spinner_item, currencyCodes
                        )
                        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        currencySpinner.adapter = currencyAdapter
                        currencySpinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                @SuppressLint("SetTextI18n")
                                override fun onItemSelected(
                                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                                ) {
                                    val selectedCurrencyCode = currencyCodes[position]
                                    binding.editTextCurrency1.clearFocus()
                                    binding.editTextCurrency2.clearFocus()
                                    binding.tvInformation2.text = "Ви ще нічого не перевели ☺"

                                    val filteredBanks = list.body()?.filter { bank ->
                                        bank.ccy == selectedCurrencyCode
                                    } ?: emptyList()

                                    adapterPrivatOther.addPrivatCurrencyDetails(filteredBanks)

                                    if (filteredBanks.isNotEmpty()) {
                                        val selectedBank = filteredBanks[0]

                                        binding.editTextCurrency1.setOnFocusChangeListener { _, hasFocus ->
                                            if (hasFocus) {
                                                binding.editTextCurrency1.text.clear()
                                                if (binding.button.rotation == 0f) {
                                                    rotationAnimator.start()
                                                }
                                                binding.editTextCurrency1.addTextChangedListener(object :
                                                    TextWatcher {
                                                    override fun beforeTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        count: Int,
                                                        after: Int
                                                    ) {
                                                    }

                                                    override fun onTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        before: Int,
                                                        count: Int
                                                    ) {
                                                        changeEditText1(selectedBank,selectedCurrencyCode)
                                                    }

                                                    override fun afterTextChanged(s: Editable?) {
                                                    }
                                                })
                                            }
                                        }

                                        binding.editTextCurrency2.setOnFocusChangeListener { _, hasFocus ->
                                            if (hasFocus) {
                                                binding.editTextCurrency2.text.clear()
                                                if (binding.button.rotation == 180f) {
                                                    rotationAnimator.reverse()
                                                }
                                                binding.editTextCurrency2.addTextChangedListener(object :
                                                    TextWatcher {
                                                    override fun beforeTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        count: Int,
                                                        after: Int
                                                    ) {
                                                    }

                                                    override fun onTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        before: Int,
                                                        count: Int
                                                    ) {
                                                        changeEditText2(selectedBank,selectedCurrencyCode)
                                                    }

                                                    override fun afterTextChanged(s: Editable?) {
                                                    }
                                                })
                                            }
                                        }

                                        binding.tvCodA.text = selectedCurrencyCode
                                        binding.tvInformation.text =
                                            "1 $selectedCurrencyCode = " + (((selectedBank.sale.toDouble()).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"

                                    }
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    // Нічого не вибрано
                                }
                            }
                    }
                } else {
                    viewModel.myCurrencyPrivatBezGot.observe(viewLifecycleOwner) { list ->
                        // Отримати унікальні коди валют зі списку банків
                        val currencyCodes = list.body()?.mapNotNull { bank ->
                            bank.ccy
                        }?.distinct() ?: emptyList()

                        val currencyAdapter = ArrayAdapter(
                            requireContext(), android.R.layout.simple_spinner_item, currencyCodes
                        )
                        currencyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        currencySpinner.adapter = currencyAdapter
                        currencySpinner.onItemSelectedListener =
                            object : AdapterView.OnItemSelectedListener {
                                @SuppressLint("SetTextI18n")
                                override fun onItemSelected(
                                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                                ) {
                                    val selectedCurrencyCode = currencyCodes[position]
                                    binding.editTextCurrency1.clearFocus()
                                    binding.editTextCurrency2.clearFocus()
                                    binding.tvInformation2.text = "Ви ще нічого не перевели ☺"

                                    val filteredBanks = list.body()?.filter { bank ->
                                        bank.ccy == selectedCurrencyCode
                                    } ?: emptyList()

                                    adapterPrivatOther.addPrivatCurrencyDetails(filteredBanks)

                                    if (filteredBanks.isNotEmpty()) {
                                        val selectedBank = filteredBanks[0]

                                        binding.editTextCurrency1.setOnFocusChangeListener { _, hasFocus ->
                                            if (hasFocus) {
                                                binding.editTextCurrency1.text.clear()
                                                if (binding.button.rotation == 0f) {
                                                    rotationAnimator.start()
                                                }
                                                binding.editTextCurrency1.addTextChangedListener(object :
                                                    TextWatcher {
                                                    override fun beforeTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        count: Int,
                                                        after: Int
                                                    ) {
                                                    }

                                                    override fun onTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        before: Int,
                                                        count: Int
                                                    ) {
                                                        changeEditText1(selectedBank,selectedCurrencyCode)
                                                    }

                                                    override fun afterTextChanged(s: Editable?) {
                                                    }
                                                })
                                            }
                                        }

                                        binding.editTextCurrency2.setOnFocusChangeListener { _, hasFocus ->
                                            if (hasFocus) {
                                                binding.editTextCurrency2.text.clear()
                                                if (binding.button.rotation == 180f) {
                                                    rotationAnimator.reverse()
                                                }
                                                binding.editTextCurrency2.addTextChangedListener(object :
                                                    TextWatcher {
                                                    override fun beforeTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        count: Int,
                                                        after: Int
                                                    ) {
                                                    }

                                                    override fun onTextChanged(
                                                        s: CharSequence?,
                                                        start: Int,
                                                        before: Int,
                                                        count: Int
                                                    ) {
                                                        changeEditText2(selectedBank,selectedCurrencyCode)
                                                    }

                                                    override fun afterTextChanged(s: Editable?) {
                                                    }
                                                })
                                            }
                                        }

                                        binding.tvCodA.text = selectedCurrencyCode
                                        binding.tvInformation.text =
                                            "1 $selectedCurrencyCode = " + (((selectedBank.sale.toDouble()).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"

                                    }
                                }

                                override fun onNothingSelected(parent: AdapterView<*>?) {
                                    // Нічого не вибрано
                                }
                            }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //
            }
        }
    }
    fun changeEditText1(selectedBank: PrivatCurrencyItem, selectedCurrencyCode: String) {
        if (!editText1ChangingByCode) {
            editText2ChangingByCode = true
            val editText1Text =
                binding.editTextCurrency1.text.toString()

            if (editText1Text.isNotEmpty()) {
                val editText1Value = editText1Text.toDouble()
                val result =
                    (((editText1Value * selectedBank.sale.toDouble()).toFloat() * 100.0).roundToInt() / 100.0)
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

            }
            editText2ChangingByCode = false
        }
    }
    fun changeEditText2(selectedBank: PrivatCurrencyItem, selectedCurrencyCode: String) {
        if (!editText2ChangingByCode) {
            editText1ChangingByCode = true
            val editText2Text =
                binding.editTextCurrency2.text.toString()
            if (editText2Text.isNotEmpty()) {
                val editText2Value = editText2Text.toDouble()
                val result =
                    (((editText2Value / selectedBank.sale.toDouble()).toFloat() * 100.0).roundToInt() / 100.0)
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
            }
            editText1ChangingByCode = false
        }
    }
}



