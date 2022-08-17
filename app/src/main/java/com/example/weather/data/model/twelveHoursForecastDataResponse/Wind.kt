package com.example.weather.data.model.twelveHoursForecastDataResponse


import com.google.gson.annotations.SerializedName

data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)