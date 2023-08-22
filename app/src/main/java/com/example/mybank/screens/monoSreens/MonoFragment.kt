package com.example.mybank.screens.monoSreens

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.mybank.R
import com.example.mybank.data.api.model.mono.MonoCurrencyItem
import com.example.mybank.databinding.FragmentMonoBinding
import java.text.NumberFormat
import kotlin.math.roundToInt


open class MonoFragment : Fragment(R.layout.fragment_mono) {
    private lateinit var binding: FragmentMonoBinding
    private var adapterMono = MonoAdapter()
    private var adapterMonoOther = MonoAdapterOther()
    lateinit var rotationAnimator: ObjectAnimator

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMonoBinding.bind(view)

        binding.recyclerViewPopular.adapter = adapterMono
        binding.recycleViewOther1.adapter = adapterMonoOther

        val viewModel = ViewModelProvider(this)[MonoViewModel::class.java]
        viewModel.getMono()

        viewModel.myCurrencyMono.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapterMono.addMonoCurrency(it) }
        }
        viewModel.myCurrencyMono.observe(viewLifecycleOwner) { list ->
            list.body()?.let { adapterMonoOther.addMonoCurrencyDetails(it) }
        }
        setupCurrencySpinner() // Виклик функції налаштування спіннера

        // Оголошення анімації обертання для ImageButton
        rotationAnimator = ObjectAnimator.ofFloat(binding.button, "rotation", 0f, 180f)
        rotationAnimator.duration = 300 // тривалість анімації
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
                    val selectedCurrencyCode = currencyCodes[position]
                    binding.editTextCurrency1.clearFocus()
                    binding.editTextCurrency2.clearFocus()
                    binding.toSite.visibility = View.GONE
                    binding.recycleViewOther1.visibility = View.VISIBLE
                    binding.tvInformation2.text = "Ви ще нічого не перевели ☺"
                    val filteredBanks = list.body()?.filter { bank ->
                        currencyConvertor = CurrencyConvertor(bank.currencyCodeA.toString())
                        currencyConvertor.currencyDetails?.first == selectedCurrencyCode
                    } ?: emptyList()

                    adapterMonoOther.addMonoCurrencyDetails(filteredBanks)


                    if (selectedCurrencyCode == "BYN") {
                        binding.editTextCurrency1.isEnabled = false
                        binding.editTextCurrency2.isEnabled = false
                        binding.recycleViewOther1.visibility = View.INVISIBLE
                        binding.toSite.visibility = View.VISIBLE
                        binding.toSite.setOnClickListener {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data =
                                Uri.parse("https://drive.google.com/file/d/1sWqD8gf5NgANNs4PQES8Vrw4idLWIgMz/view?usp=sharing")
                            startActivity(intent)
                        }

                    } else {
                        if (filteredBanks.isNotEmpty()) {
                            val selectedBank = filteredBanks[0]
                            binding.editTextCurrency1.isEnabled = true
                            binding.editTextCurrency2.isEnabled = true

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
                            if (selectedBank.rateSell != 0.0) {
                                binding.tvInformation.text =
                                    "1 $selectedCurrencyCode = " + (((selectedBank.rateSell).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
                            } else {
                                binding.tvInformation.text =
                                    "1 $selectedCurrencyCode = " + (((selectedBank.rateCross).toFloat() * 100.0).roundToInt() / 100.0).toString() + " грн"
                            }
                        } else {
                            binding.tvInformation.text = ""
                        }


                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Нічого не вибрано
                }

            }

        }

    }
    fun changeEditText1(selectedBank: MonoCurrencyItem, selectedCurrencyCode: String) {
        val editText1Text =
            binding.editTextCurrency1.text.toString()
        if (editText1Text.isNotEmpty()) {
            val editText1Value = editText1Text.toDouble()
            if (selectedBank.rateSell != 0.0) {
                val result =
                    (((editText1Value * selectedBank.rateSell).toFloat() * 100.0).roundToInt() / 100.0)
                binding.editTextCurrency2.setText(result.toString())
            } else {
                val result =
                    (((editText1Value * selectedBank.rateCross).toFloat() * 100.0).roundToInt() / 100.0)
                binding.editTextCurrency2.setText(result.toString())
            }

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
//            Toast.makeText(
//                requireContext(),
//                "Будь ласка, введіть суму, яку потрібно перевести",
//                Toast.LENGTH_SHORT
//            ).show()
        }
    }

    fun changeEditText2(selectedBank: MonoCurrencyItem, selectedCurrencyCode: String) {
        val editText2Text =
            binding.editTextCurrency2.text.toString()
        if (editText2Text.isNotEmpty()) {
            val editText2Value = editText2Text.toDouble()
            if (selectedBank.rateSell != 0.0) {
                val result =
                    (((editText2Value / selectedBank.rateSell).toFloat() * 100.0).roundToInt() / 100.0)
                binding.editTextCurrency1.setText(result.toString())
            } else {
                val result =
                    (((editText2Value / selectedBank.rateCross).toFloat() * 100.0).roundToInt() / 100.0)
                binding.editTextCurrency1.setText(result.toString())
            }
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
//            Toast.makeText(
//                requireContext(),
//                "Будь ласка, введіть суму, яку потрібно перевести",
//                Toast.LENGTH_SHORT
//            ).show()
        }
    }
}