package com.example.weather.data.repository

import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import com.example.weather.data.remote.ApiService
import com.example.weather.domain.repository.RepositoryT
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiServiceOpen: ApiService,
    private val apiServiceAccu: ApiService,
    private val accuKey: String,
    private val openKey: String
    ) : RepositoryT {

    override suspend fun getCitiesListAutocomplete(city: String): Response<CitiesListAutocomplete> {
        return apiServiceAccu.getCitiesListAutocomplete(accuKey, city)
    }

    override suspend fun getFiveDaysForecastByCoordinates(data: DataForCoordinatesSearch): Response<TwelveHoursForecastDataResponse> {
        return apiServiceOpen.getFiveDaysForecastByCoordinates(
            data.latitude,
            data.longitude,
            data.count,
            openKey,
            data.units
        )
    }

    override suspend fun getFiveDaysForecastByCityName(data: DataForCoordinatesSearch): Response<TwelveHoursForecastDataResponse> {
        return apiServiceOpen.getFiveDaysForecastByCityName(
            data.cityName + ',' + data.country,
            data.count,
            openKey,
            data.units
        )
    }
}