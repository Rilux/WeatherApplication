package com.example.weather.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val city: String,
    val country: String,
    val iso3: String,
) : Parcelable
