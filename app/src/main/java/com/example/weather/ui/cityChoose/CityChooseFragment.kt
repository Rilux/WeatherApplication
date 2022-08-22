package com.example.weather.ui.cityChoose

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.weather.R
import com.example.weather.data.model.City
import com.example.weather.data.model.DataForCoordinatesSearch
import com.example.weather.databinding.FragmentCityChooseBinding
import com.example.weather.ui.SharedViewModel
import com.example.weather.ui.checkForInternet
import com.example.weather.ui.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CityChooseFragment : Fragment(R.layout.fragment_city_choose) {

    private lateinit var binding: FragmentCityChooseBinding
    private val cityChooseFragmentViewModel: CityChooseFragmentViewModel by viewModels()

    private lateinit var adapter: CityChooseAdapter
    private lateinit var recyclerview: RecyclerView
    private val sharedViewModel: SharedViewModel by activityViewModels()

    val layoutManager = LinearLayoutManager(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityChooseBinding.bind(view)


        recyclerview = binding.recyclerview1


        recyclerview.layoutManager = layoutManager

        val viewpager = activity?.findViewById<ViewPager2>(R.id.view_pagerMain)
        adapter = CityChooseAdapter(object : CityChooseListener {
            override fun onCityClicked(city: City) {
                sharedViewModel.setCityData(
                    DataForCoordinatesSearch().copy(
                        cityName = city.city,
                        country = city.iso2,
                        cityApiKey = city.cityKey
                    )
                )

                Log.d("MyLog", "City: {$city}")
                viewpager?.currentItem = 0
            }
        })

        recyclerview.adapter = adapter

        cityChooseFragmentViewModel.apiData.observe(viewLifecycleOwner, Observer {
            val listCity = mutableListOf<City>()
            it.forEach {
                listCity.add(
                    City(
                        it.localizedName,
                        it.country.localizedName,
                        it.key,
                        it.country.iD
                    )
                )
            }
            adapter.setList(listCity)
        })


        binding.defaultEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                lifecycleScope.launch() {
                    if (s.isNotEmpty()) {
                        cityTextChanged(s)
                    }
                }

            }
        })
    }

    fun cityTextChanged(s: CharSequence) {
        if (context?.let { checkForInternet(it) } == true) {
            cityChooseFragmentViewModel.cityTextChanged(s)
        } else {
            showToast(
                "Oops, something went wrong, please, check your Internet connection and try again",
                1
            )
        }

    }

    companion object {
        const val CITY_CODE = "CODE_BACK_CITY"
        const val CITY_DATA = "CITY_DATA"
    }
}

