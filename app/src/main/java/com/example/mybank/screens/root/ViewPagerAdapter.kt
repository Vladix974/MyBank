package com.example.mybank.screens.root

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mybank.screens.dollars.DollarsFragment
import com.example.mybank.screens.euro.EuroFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->DollarsFragment()
            else-> EuroFragment()
        }
    }
}