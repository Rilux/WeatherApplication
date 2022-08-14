package com.example.weather.data.model.citiesListAutocompleteResponse


import com.google.gson.annotations.SerializedName

data class CitiesListAutocompleteItem(
    @SerializedName("Version")
    val version: Int,
    @SerializedName("Key")
    val key: String,
    @SerializedName("Type")
    val type: String,
    @SerializedName("Rank")
    val rank: Int,
    @SerializedName("LocalizedName")
    val localizedName: String,
    @SerializedName("Country")
    val country: Country,
    @SerializedName("AdministrativeArea")
    val administrativeArea: AdministrativeArea
)