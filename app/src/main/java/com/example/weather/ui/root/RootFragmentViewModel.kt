package com.example.weather.ui.root

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import com.example.weather.data.repository.Repository
import kotlinx.coroutines.launch

class RootFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = Repository()

    private var _apiData = MutableLiveData<TwelveHoursForecastDataResponse>()
    val apiData: MutableLiveData<TwelveHoursForecastDataResponse> = _apiData

    var cityData: DataForCoordinatesSearch = DataForCoordinatesSearch()

    private fun callToApi(case: Boolean) {
        viewModelScope.launch {
            when (case) {
                true -> {
                    _apiData.value = repo.getFiveDaysForecastByCityName(cityData).body()
                }
                false -> {
                    _apiData.value = repo.getFiveDaysForecastByCoordinates(cityData).body()
                }
            }

        }
    }

    fun cityChanged(data: DataForCoordinatesSearch, case: Boolean) {
        cityData = data
        callToApi(case)
    }
}