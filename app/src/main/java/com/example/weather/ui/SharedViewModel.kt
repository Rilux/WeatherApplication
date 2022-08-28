package com.example.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse

class SharedViewModel : ViewModel() {

    private var _forecastData = MutableLiveData<TwelveHoursForecastDataResponse>()
    val forecastData: LiveData<TwelveHoursForecastDataResponse> = _forecastData

    private var _cityData = MutableLiveData<DataForCoordinatesSearch>()
    val cityData: LiveData<DataForCoordinatesSearch> = _cityData


    fun setForecastData(data: TwelveHoursForecastDataResponse) {
        _forecastData.value = data
    }

    fun setCityData(data: DataForCoordinatesSearch) {
        _cityData.value = data
    }

}