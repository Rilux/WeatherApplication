package com.example.weather.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.weather.R
import com.example.weather.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.internal.managers.FragmentComponentManager

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.viewPagerMain.adapter = MainViewPagerAdapter(FragmentComponentManager.findActivity(view.context) as FragmentActivity)

        binding.viewPagerMain.isUserInputEnabled = false
        TabLayoutMediator(binding.tabLayout, binding.viewPagerMain){
            tab, pos -> when(pos){
                0 -> {
                    tab.text = "Today Weather"
                }
                1 -> {
                    tab.text = "Five Day Weather"
                }
                2 -> {
                    tab.text = "Change City"
                }
            }
        }.attach()
    }


}