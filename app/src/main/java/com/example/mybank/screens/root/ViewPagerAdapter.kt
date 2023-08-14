package com.example.mybank.screens.root

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mybank.screens.privatSreens.PrivatFragment
import com.example.mybank.screens.monoSreens.MonoFragment
import com.example.mybank.screens.nbuScreens.NbuFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->MonoFragment()
            1-> NbuFragment()
            else-> PrivatFragment()
        }
    }
}