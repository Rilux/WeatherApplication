package com.example.weather.di

import com.example.weather.BuildConfig
import com.example.weather.data.remote.ApiService
import com.example.weather.data.repository.RepositoryImpl
import com.example.weather.domain.repository.RepositoryT
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    @Named("ApiServiceOpen")
    fun provideApiServiceOpenWeather(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("ApiServiceAccu")
    fun provideApiServiceAccu(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://dataservice.accuweather.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(
        @Named("ApiServiceOpen") api1: ApiService,
        @Named("ApiServiceAccu") api2: ApiService,
        @Named("apiKeyAccuWeather") key1: String,
        @Named("apiKeyOpenWeather") key2: String
    ): RepositoryT {
        return RepositoryImpl(api1, api2, key1, key2)
    }

    @Provides
    @Singleton
    @Named("apiKeyOpenWeather")
    fun providesApiOpenWeatherKey(): String {
        return "4d88423d5140e8d43436999c139cbf8d"
    }

    @Provides
    @Singleton
    @Named("apiKeyAccuWeather")
    fun providesApiAccuWeatherKey(): String {
        return "xY7J0GGAQmyDlbYz9aTAncGFZj1fxGOD"
    }

    @Provides
    @Singleton
    fun providesClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();
    }

    @Provides
    @Singleton
    fun providesInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(
            if
                    (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )
    }

}