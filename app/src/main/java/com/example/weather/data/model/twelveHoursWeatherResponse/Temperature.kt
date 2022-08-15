package com.example.weather.data.model.twelveHoursWeatherResponse


import com.google.gson.annotations.SerializedName

data class Temperature(
    @SerializedName("Value")
    val value: Double,
    @SerializedName("Unit")
    val unit: String,
    @SerializedName("UnitType")
    val unitType: Int
)