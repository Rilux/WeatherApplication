package com.example.weather.ui.cityChoose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.local.citiesListResponse.Data
import com.example.weather.data.repository.Repository
import kotlinx.coroutines.launch


class CityChooseFragmentViewModel : ViewModel() {

    private var _apiData = MutableLiveData<List<Data>>()
    val apiData: MutableLiveData<List<Data>> = _apiData

    private val repo = Repository()
    private var localListWithData = listOf<Data>()


    fun cityTextChanged(tempText: CharSequence) {
        val filteredData = mutableListOf<Data>()

        localListWithData.forEach {
            val citiesList: List<String> = it.cities.filter {
                it.startsWith(tempText)
            }
            if (citiesList.isNotEmpty()) {
                filteredData.add(it.copy(cities = citiesList))
            }
        }
        _apiData.value = filteredData
    }


    init {
        callToApi()
    }

    private fun callToApi() {
        viewModelScope.launch {
            val temp: List<Data>? = repo.getCitiesList().body()?.data
            if (temp != null) {
                localListWithData = temp
            }
        }
    }


}