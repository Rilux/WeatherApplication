package com.example.weather.data.repository

import com.example.weather.data.BasicApiLoginData
import com.example.weather.data.remote.RetrofitInstance
import com.example.weather.data.local.oneDayResponse.OneDayWeatherDataResponse
import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
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

    //Get one day weather forecast
    suspend fun getOneDayWeather(city: String, units: String): Response<OneDayWeatherDataResponse> {
        return RetrofitInstance.weatherApi.getOneDayWeather(city, BasicApiLoginData.apiCode, units)
    }

}