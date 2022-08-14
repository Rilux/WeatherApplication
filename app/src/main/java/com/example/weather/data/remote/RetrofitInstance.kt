package com.example.weather.data.remote

import com.example.weather.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitInstance {

    var interceptor =
        HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)

    var client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build();

    private val retrofitCallToCitiesListApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://countriesnow.space/api/v0.1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private val retrofitCallToWeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    private val retrofitCallToAccuWeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    val citiesApi: ApiService by lazy {
        retrofitCallToCitiesListApi.create(ApiService::class.java)
    }

    val weatherApi: ApiService by lazy {
        retrofitCallToWeatherApi.create(ApiService::class.java)
    }

    val accWweatherApi: ApiService by lazy {
        retrofitCallToAccuWeatherApi.create(ApiService::class.java)
    }
}