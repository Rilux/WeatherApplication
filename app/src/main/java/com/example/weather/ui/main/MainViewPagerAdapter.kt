package com.example.weather.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weather.ui.BlankFragment
import com.example.weather.ui.cityChoose.CityChooseFragment
import com.example.weather.ui.root.RootFragment

class MainViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RootFragment()
            1 -> BlankFragment()
            else -> CityChooseFragment()
        }
    }
}