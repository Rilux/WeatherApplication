package com.example.weather.data.remote

import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


public interface ApiService {


    //Call to get updatable list of the cities by text entered in the edit text field on CityChooseFragment
    //http://dataservice.accuweather.com/locations/v1/cities/autocomplete?apikey=xY7J0GGAQmyDlbYz9aTAncGFZj1fxGOD&q=K"
    @GET("locations/v1/cities/autocomplete")
    suspend fun getCitiesListAutocomplete(
        @Query("apikey") apikey: String,
        @Query("q") cityQuote: String
    ): Response<CitiesListAutocomplete>

    //Call to get five days forecast with 3 hours range between forecasts
    //https://api.openweathermap.org/data/2.5/forecast?lat=57&lon=-2.15&cnt=12&appid=4d88423d5140e8d43436999c139cbf8d&units=metric
    @GET("forecast")
    suspend fun getFiveDaysForecastByCoordinates(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("cnt") numberOfEvents: String,
        @Query("appid") apiKey: String,
        @Query("units") metric: String
    ): Response<TwelveHoursForecastDataResponse>

    //Call to get weather data for one day by city name and specifying units metric/imperial
    //https://api.openweathermap.org/data/2.5/forecast?q=Kharkiv,UA&cnt=3&appid=4d88423d5140e8d43436999c139cbf8d&units=metric
    @GET("forecast")
    suspend fun getFiveDaysForecastByCityName(
        @Query("q") cityName: String,
        @Query("cnt") numberOfEvents: String,
        @Query("appid") apiKey: String,
        @Query("units") metric: String
    ): Response<TwelveHoursForecastDataResponse>

}