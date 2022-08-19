package com.example.weather.data.model

data class DataForFiveDaysWeatherForecast(
    var tempMax: Double = Double.MIN_VALUE,
    var tempMin: Double = Double.MAX_VALUE,
    var dateTxt: String = "0",
    var image: String = "0"
)
