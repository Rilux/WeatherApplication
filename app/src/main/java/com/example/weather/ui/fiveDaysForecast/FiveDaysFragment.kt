package com.example.weather.ui.fiveDaysForecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.weather.R
import com.example.weather.databinding.FragmentFiveDaysBinding
import com.example.weather.ui.SharedViewModel

class FiveDaysFragment : Fragment(R.layout.fragment_five_days) {

    lateinit var binding: FragmentFiveDaysBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFiveDaysBinding.bind(view)

    }

}