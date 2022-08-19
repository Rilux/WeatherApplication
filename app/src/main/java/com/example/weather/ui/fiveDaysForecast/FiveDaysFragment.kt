package com.example.weather.ui.fiveDaysForecast

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.FragmentFiveDaysBinding
import com.example.weather.ui.SharedViewModel

class FiveDaysFragment : Fragment(R.layout.fragment_five_days) {

    lateinit var binding: FragmentFiveDaysBinding
    private lateinit var viewModel: FiveDaysFragmentViewModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var adapter: FiveDaysFragmentAdapter
    private lateinit var recyclerview: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFiveDaysBinding.bind(view)

        viewModel = ViewModelProvider(this)[FiveDaysFragmentViewModel::class.java]

        setRecycler()

        sharedViewModel.forecastData.observe(viewLifecycleOwner) {
            viewModel.calculateDataForUi(it)
        }

        viewModel.forecastData.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })
    }


    private fun setRecycler() {
        recyclerview = binding.rvFiveDaysForecast
        adapter = FiveDaysFragmentAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}