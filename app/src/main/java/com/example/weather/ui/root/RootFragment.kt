package com.example.weather.ui.root

import android.Manifest
import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.City
import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import com.example.weather.databinding.FragmentRootBinding
import com.example.weather.ui.checkForInternet
import com.example.weather.ui.cityChoose.CityChooseFragment
import com.example.weather.ui.isPermissionGranted
import com.example.weather.ui.showToast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.internal.wait


class RootFragment : Fragment(R.layout.fragment_root) {


    private lateinit var binding: FragmentRootBinding
    private lateinit var rootViewModel: RootFragmentViewModel
    private var dataForWeather = DataForCoordinatesSearch()

    private lateinit var adapter: RootFragmentAdapter
    private lateinit var recyclerview: RecyclerView

    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            launch { locationOperations() }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRootBinding.bind(view)

        rootViewModel = ViewModelProvider(this)[RootFragmentViewModel::class.java]

        drawRecyclerView()
        checkPermission()

        getForecastByCoordinates()


        rootViewModel.apiData.observe(viewLifecycleOwner, Observer {
            toScreen(it)
            setDataForSearch(
                dataForWeather.copy(
                    cityName = it.city.name,
                    country = it.city.country,
                    longitude = it.city.coord.lon.toString(),
                    latitude = it.city.coord.lat.toString()
                )
            )
            adapter.setList(it)
        })

        binding.floatingRefreshButton.setOnClickListener {
            refreshData(dataForWeather, true)
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
                setDataForSearch(
                    dataForWeather.copy(
                        cityName = city.city,
                        units = "metric",
                        cityApiKey = city.cityKey,
                        country = city.iso2
                    )
                )
                refreshData(
                    dataForWeather, true
                )
            }
        }

    }

    //True - call to get forecast data by city code(CityApi)
    //False - call to get weather forecast data by coordinates
    private fun refreshData(data: DataForCoordinatesSearch, case: Boolean) {
        if (context?.let { checkForInternet(it) } == true) {
            rootViewModel.cityChanged(data, case)
        } else {
            showToast(
                "Oops, something went wrong, please, check your Internet connection and try again",
                1
            )
        }
    }

    private fun toScreen(data: TwelveHoursForecastDataResponse) {
        binding.temperatureProgressBarMainFragment.visibility = View.VISIBLE
        binding.temperatureXML.visibility = View.GONE

        binding.currentCityName.text = data.city.name
        binding.temperatureXML.text = Math.round(data.list[0].main.temp).toString() + "°C"

        binding.temperatureXML.visibility = View.VISIBLE
        binding.temperatureProgressBarMainFragment.visibility = View.GONE
    }

    private fun drawRecyclerView() {
        recyclerview = binding.recyclerviewFiveDayWeather
        adapter = RootFragmentAdapter()
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    }

    private fun toCityChangeScreen() {
        findNavController().navigate(R.id.action_rootFragment_to_cityChooseFragment)
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                showToast("Permission is granted", 1)
            }
        }
    }

    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun locationOperations() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    setDataForSearch(
                        dataForWeather.copy(
                            latitude = location.latitude.toString(),
                            longitude = location.longitude.toString()
                        )
                    )
                    getForecastByCoordinates()
                    Log.d("MyLog", location.latitude.toString() + location.longitude.toString())
                }
            }
        }
    }

    private fun setDataForSearch(data: DataForCoordinatesSearch) {
        dataForWeather = data
    }

    private fun getForecastByCoordinates() {
        if (isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            refreshData(
                dataForWeather, false
            )
        }
    }
}