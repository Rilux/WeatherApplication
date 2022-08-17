package com.example.weather.data.repository

import com.example.weather.data.remote.RetrofitInstance
import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import retrofit2.Response

class Repository {


    //Get updatable list of the cities by text entered in the edit text field on CityChooseFragment
    //http://dataservice.accuweather.com/locations/v1/cities/autocomplete?apikey=xY7J0GGAQmyDlbYz9aTAncGFZj1fxGOD&q=K"
    suspend fun getCitiesListAutocomplete(city: String): Response<CitiesListAutocomplete> {
        return RetrofitInstance.accWweatherApi.getCitiesListAutocomplete(
            "xY7J0GGAQmyDlbYz9aTAncGFZj1fxGOD",
            //BasicApiLoginData.apiCode,
            city
        )
    }

    suspend fun getFiveDaysForecastByCoordinates(data: DataForCoordinatesSearch): Response<TwelveHoursForecastDataResponse> {
        return RetrofitInstance.weatherApi.getFiveDaysForecastByCoordinates(
            data.latitude,
            data.longitude,
            data.count,
            DataForCoordinatesSearch.apiCode,
            data.units
        )
    }

    suspend fun getFiveDaysForecastByCityName(data: DataForCoordinatesSearch): Response<TwelveHoursForecastDataResponse> {
        return RetrofitInstance.weatherApi.getFiveDaysForecastByCityName(
            data.cityName + ',' + data.country,
            data.count,
            DataForCoordinatesSearch.apiCode,
            data.units
        )
    }
}