package com.example.weather.ui.root

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.BasicApiLoginData
import com.example.weather.data.repository.Repository
import com.example.weather.data.local.oneDayResponse.OneDayWeatherDataResponse
import kotlinx.coroutines.launch

class RootFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repository()

    private var _apiData = MutableLiveData<OneDayWeatherDataResponse>()
    val apiData: MutableLiveData<OneDayWeatherDataResponse> = _apiData

    var cityName: BasicApiLoginData = BasicApiLoginData()

    private fun callToApi() {
        viewModelScope.launch {
            val temp = repo.getOneDayWeather(cityName.cityName, cityName.units)
            _apiData.value = temp.body()
            Log.d("MyLog", "Temperature: ${_apiData.value?.main?.temp ?: "nullable"}")
        }
    }

    init {
        callToApi()
    }

    fun cityChanged(cityTemp: String) {
        cityName.cityName = cityTemp
        callToApi()
    }
}