package com.example.weather.ui.fiveDaysForecast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.data.model.DataForFiveDaysWeatherForecast
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class FiveDaysFragmentViewModel : ViewModel() {

    private var _forecastData = MutableLiveData<List<DataForFiveDaysWeatherForecast>>()
    val forecastData: MutableLiveData<List<DataForFiveDaysWeatherForecast>> = _forecastData

    fun calculateDataForUi(data: TwelveHoursForecastDataResponse) {
        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        var time = LocalDate.now()
        val tempList = mutableListOf<DataForFiveDaysWeatherForecast>()

        var forecastTemp = DataForFiveDaysWeatherForecast()
        data.list.forEach {
            if (LocalDateTime.parse(it.dtTxt, pattern).toLocalDate() != time) {
                forecastTemp.dateTxt = time.toString()
                forecastTemp.image = it.weather[0].icon
                tempList.add(forecastTemp)
                forecastTemp = DataForFiveDaysWeatherForecast()
                time = LocalDateTime.parse(it.dtTxt, pattern).toLocalDate()
            }
            if (it.main.temp > forecastTemp.tempMax) {
                forecastTemp.tempMax = it.main.temp
            }
            if (it.main.temp < forecastTemp.tempMin) {
                forecastTemp.tempMin = it.main.temp
            }
        }

        _forecastData.value = tempList

    }
}