package com.example.weather.ui.cityChoose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
import com.example.weather.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch


class CityChooseFragmentViewModel : ViewModel() {

    private var _apiData = MutableLiveData<CitiesListAutocomplete>()
    val apiData: MutableLiveData<CitiesListAutocomplete> = _apiData

    private val repo = Repository()


    fun cityTextChanged(tempText: CharSequence) {
        viewModelScope.launch {
            val apiResult = callToApi(tempText.toString()).single()
            val temp = apiResult
            if (temp != null){
                _apiData.value = temp ?: CitiesListAutocomplete()
            }
        }
    }

    private fun callToApi(cityName: String): Flow<CitiesListAutocomplete?> =
        flow {
            val temp = repo.getCitiesListAutocomplete(cityName).body()
            emit(temp)
        }.flowOn(Dispatchers.Default)
}