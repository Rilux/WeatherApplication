package com.example.weather.data.model.twelveHoursForecastDataResponse


import com.google.gson.annotations.SerializedName

data class TwelveHoursForecastDataResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<ForecastItem>,
    val city: City
)