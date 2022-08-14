package com.example.weather.data.remote

import com.example.weather.data.local.citiesListResponse.CitiesListGlobal
import com.example.weather.data.local.oneDayResponse.OneDayWeatherDataResponse
import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


public interface ApiService {

    //Call to get all the cities and countries they belong
    //https://countriesnow.space/api/v0.1/countries
    @GET("countries")
    suspend fun getCitiesList(): Response<CitiesListGlobal>


    //http://dataservice.accuweather.com/locations/v1/cities/autocomplete?apikey=xY7J0GGAQmyDlbYz9aTAncGFZj1fxGOD&q=K"
    @GET("locations/v1/cities/autocomplete")
    suspend fun getCitiesListAutocomplete(
        @Query("apikey") apikey: String,
        @Query("q") cityQuote: String
    ): Response<CitiesListAutocomplete>


    //Call to get weather data for one day by city name and specifying units metric/imperial
    //api.openweathermap.org/data/2.5/forecast?q={city name}&appid={API key}
    @GET("weather")
    suspend fun getOneDayWeather(
        @Query("q") cityNameOneDayRetrofit: String,
        @Query("appid") apiOneDayRetrofit: String,
        @Query("units") unitsOneDayRetrofit: String
    ): Response<OneDayWeatherDataResponse>

}