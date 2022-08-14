package com.example.weather.data.local.citiesListResponse


data class Data(
    val iso2: String,
    val iso3: String,
    val country: String,
    var cities: List<String>
)