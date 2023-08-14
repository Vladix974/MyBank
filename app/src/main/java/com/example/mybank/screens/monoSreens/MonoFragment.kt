package com.example.mybank.screens.monoSreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.mybank.R
import com.example.mybank.data.api.model.mono.MonoCurrencyItem
import com.example.mybank.databinding.FragmentMonoBinding

class MonoFragment : Fragment(R.layout.fragment_mono) {
    private lateinit var binding : FragmentMonoBinding
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
    }

}