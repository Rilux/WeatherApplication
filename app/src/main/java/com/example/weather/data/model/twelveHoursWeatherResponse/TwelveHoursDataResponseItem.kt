package com.example.weather.data.model.twelveHoursWeatherResponse


import com.google.gson.annotations.SerializedName
import java.util.*

data class TwelveHoursDataResponseItem(
    @SerializedName("DateTime")
    val dateTime: String,
    @SerializedName("EpochDateTime")
    val epochDateTime: Int,
    @SerializedName("WeatherIcon")
    val weatherIcon: Int,
    @SerializedName("IconPhrase")
    val iconPhrase: String,
    @SerializedName("HasPrecipitation")
    val hasPrecipitation: Boolean,
    @SerializedName("IsDaylight")
    val isDaylight: Boolean,
    @SerializedName("Temperature")
    val temperature: Temperature,
    @SerializedName("PrecipitationProbability")
    val precipitationProbability: Int,
    @SerializedName("MobileLink")
    val mobileLink: String,
    @SerializedName("Link")
    val link: String
)