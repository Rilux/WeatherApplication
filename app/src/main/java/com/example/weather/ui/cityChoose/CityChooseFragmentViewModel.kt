package com.example.weather.ui.cityChoose

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.data.model.citiesListAutocompleteResponse.CitiesListAutocomplete
import com.example.weather.domain.repository.RepositoryT
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityChooseFragmentViewModel @Inject constructor(
    private val repo: Lazy<RepositoryT>
) : ViewModel() {

    private var _apiData = MutableLiveData<CitiesListAutocomplete>()
    val apiData: LiveData<CitiesListAutocomplete> = _apiData


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
            val temp = repo.get().getCitiesListAutocomplete(cityName).body()
            emit(temp)
        }.flowOn(Dispatchers.Default)
}