package com.example.weather.ui.oneDay

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import com.example.weather.domain.repository.RepositoryT
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneDayFragmentViewModel @Inject constructor(
    private val repo: Lazy<RepositoryT>,
    private var cityData: DataForCoordinatesSearch
) : ViewModel() {

    private var _apiData = MutableLiveData<TwelveHoursForecastDataResponse>()
    val apiData: LiveData<TwelveHoursForecastDataResponse> = _apiData


    private fun callToApi(case: Boolean) {
        viewModelScope.launch {
            when (case) {
                true -> {
                    _apiData.value = repo.get().getFiveDaysForecastByCityName(cityData).body()
                }
                false -> {
                    _apiData.value = repo.get().getFiveDaysForecastByCoordinates(cityData).body()
                }
            }

        }
    }

    fun cityChanged(data: DataForCoordinatesSearch, case: Boolean) {
        cityData = data
        callToApi(case)
    }
}