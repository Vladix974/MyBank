package com.example.mybank.screens.privatSreens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mybank.R
import com.example.mybank.databinding.FragmentPrivatBinding
import kotlinx.coroutines.launch

//import com.example.mybank.databinding.FragmentDollarsBinding

class PrivatFragment : Fragment(R.layout.fragment_privat) {
    private lateinit var binding: FragmentPrivatBinding
    private var adapterPrivat = PrivatAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrivatBinding.bind(view)

        binding.rvCurrenties.adapter = adapterPrivat

        val viewModel = ViewModelProvider(this)[PrivatViewModel::class.java]
        viewModel.getPrivat()
        viewModel.myCurrencyPrivat.observe(viewLifecycleOwner){list->
            list.body()?.let { adapterPrivat.addCarrency(it) }
        }

    }


}
