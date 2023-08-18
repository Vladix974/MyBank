package com.example.mybank.screens.nbuScreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mybank.R
import com.example.mybank.databinding.FragmentNbuBinding

class NbuFragment: Fragment(R.layout.fragment_nbu) {
    private lateinit var binding: FragmentNbuBinding
    private var adapterNbu = NbuAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNbuBinding.bind(view)

        binding.recyclerViewPopular.adapter = adapterNbu

        val viewModel = ViewModelProvider(this)[NbuViewModel::class.java]

        viewModel.getNbu()

        viewModel.myCurrencyNbu.observe(viewLifecycleOwner){list->
            list.body()?.let { adapterNbu.addNbuCurrency(it) }
        }
    }
}