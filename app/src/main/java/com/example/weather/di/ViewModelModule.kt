package com.example.weather.di

import com.example.weather.data.model.DataForCoordinatesSearch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideDataForCoordinatesSearch(): DataForCoordinatesSearch = DataForCoordinatesSearch()

}