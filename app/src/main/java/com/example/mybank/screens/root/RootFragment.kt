package com.example.mybank.screens.root

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.mybank.R
import com.example.mybank.databinding.FragmentRootBinding
import com.google.android.material.tabs.TabLayoutMediator

class RootFragment : Fragment(R.layout.fragment_root) {
    lateinit var binding: FragmentRootBinding

    private var ctx: Context? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        ctx = context
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        binding.tabLayout.tabIconTint = null
        binding.viewPager.adapter = ViewPagerAdapter(ctx as FragmentActivity)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, pos ->
            when (pos) {
                0 -> {
                    tab.setIcon(R.drawable.mono_logo)
                }
                1 -> {
                    tab.setIcon(R.drawable.nbu_logo)
                }
                2->{
                    tab.setIcon(R.drawable.privat_logo)
                }
            }
        }.attach()
    }

}
