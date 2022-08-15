package com.example.weather.ui.root

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.BasicApiLoginData
import com.example.weather.data.local.oneDayResponse.OneDayWeatherDataResponse
import com.example.weather.data.model.City
import com.example.weather.data.model.twelveHoursWeatherResponse.TwelveHoursDataResponse
import com.example.weather.databinding.FragmentRootBinding
import com.example.weather.ui.cityChoose.CityChooseFragment


class RootFragment : Fragment(R.layout.fragment_root) {


    private lateinit var binding: FragmentRootBinding
    private lateinit var rootViewModel: RootFragmentViewModel
    private var dataForWeather = BasicApiLoginData()

    private lateinit var adapter: RootFragmentAdapter
    private lateinit var recyclerview: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        drawRecyclerView()

        rootViewModel = ViewModelProvider(this)[RootFragmentViewModel::class.java]
        rootViewModel.apiData.observe(viewLifecycleOwner, Observer {
            toScreen(it)
            adapter.setList(it)
        })


        binding.floatingRefreshButton.setOnClickListener {
            refreshData(dataForWeather)
        }

        binding.currentCityName.setOnClickListener {
            toCityChangeScreen()
        }

        parentFragmentManager.setFragmentResultListener(
            CityChooseFragment.CITY_CODE,
            viewLifecycleOwner
        ) { _, data ->
            val city: City? = data.getParcelable(CityChooseFragment.CITY_DATA)
            Log.d("MyLog", "City1: {${city}}")
            if (city != null) {
                dataForWeather = BasicApiLoginData(city.city, true, city.cityKey)
                refreshData(
                    BasicApiLoginData(
                        dataForWeather.cityName,
                        dataForWeather.units,
                        dataForWeather.cityApiKey
                    )
                )
            }
        }

    }

    fun refreshData(data: BasicApiLoginData) {
        rootViewModel.cityChanged(data.cityApiKey)
        binding.currentCityName.text = data.cityName

    }

    fun toScreen(data: TwelveHoursDataResponse) {
        binding.temperatureProgressBarMainFragment.visibility = View.VISIBLE
        binding.temperatureXML.visibility = View.GONE

        binding.temperatureXML.text = Math.round(data[0].temperature.value).toString() + "°C"

        binding.temperatureXML.visibility = View.VISIBLE
        binding.temperatureProgressBarMainFragment.visibility = View.GONE
    }

    fun toCityChangeScreen() {
        findNavController().navigate(R.id.action_rootFragment_to_cityChooseFragment)
    }

    fun drawRecyclerView() {
        recyclerview = binding.recyclerviewFiveDayWeather
        adapter = RootFragmentAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }
}