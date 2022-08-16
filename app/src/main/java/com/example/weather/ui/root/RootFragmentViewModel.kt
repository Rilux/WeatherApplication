package com.example.weather.ui.root

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.data.BasicApiLoginData
import com.example.weather.data.repository.Repository
import com.example.weather.data.model.twelveHoursWeatherResponse.TwelveHoursDataResponse
import kotlinx.coroutines.launch

class RootFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repository()

    private var _apiData = MutableLiveData<TwelveHoursDataResponse>()
    val apiData: MutableLiveData<TwelveHoursDataResponse> = _apiData

    var cityName: BasicApiLoginData = BasicApiLoginData()

    private fun callToApi() {
        viewModelScope.launch {
            val temp = repo.getTwelveHoursWeatherData(cityName.cityApiKey)
            _apiData.value = temp.body()
        }
    }

    fun cityChanged(cityTemp: String) {
        cityName.cityApiKey = cityTemp
        callToApi()
    }
}