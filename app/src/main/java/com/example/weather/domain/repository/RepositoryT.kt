package com.example.weather.domain.repository

import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import retrofit2.Response
import javax.inject.Named

interface RepositoryT {
    suspend fun getCitiesListAutocomplete(city: String): Response<CitiesListAutocomplete>
    suspend fun getFiveDaysForecastByCoordinates(data: DataForCoordinatesSearch): Response<TwelveHoursForecastDataResponse>
    suspend fun getFiveDaysForecastByCityName(data: DataForCoordinatesSearch): Response<TwelveHoursForecastDataResponse>
}