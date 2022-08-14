package com.example.weather.data.local.citiesListResponse


data class CitiesListGlobal(
    val error: Boolean,
    val msg: String,
    val `data`: List<Data>
)