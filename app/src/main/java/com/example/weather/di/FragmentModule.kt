package com.example.weather.di

import com.example.weather.data.model.DataForCoordinatesSearch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    @FragmentScoped
    fun provideDataForSearch(): DataForCoordinatesSearch = DataForCoordinatesSearch()

}