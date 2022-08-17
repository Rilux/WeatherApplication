package com.example.weather.ui.cityChoose

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.City
import com.example.weather.databinding.FragmentCityChooseBinding
import com.example.weather.ui.checkForInternet
import com.example.weather.ui.showToast
import kotlinx.coroutines.launch


class CityChooseFragment : Fragment(R.layout.fragment_city_choose) {

    private lateinit var binding: FragmentCityChooseBinding
    private lateinit var cityChooseFragmentViewModel: CityChooseFragmentViewModel

    private lateinit var adapter: CityChooseAdapter
    private lateinit var recyclerview: RecyclerView

    val layoutManager = LinearLayoutManager(context)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCityChooseBinding.bind(view)

        cityChooseFragmentViewModel =
            ViewModelProvider(this)[CityChooseFragmentViewModel::class.java]

        recyclerview = binding.recyclerview1


        recyclerview.layoutManager = layoutManager

        adapter = CityChooseAdapter(object : CityChooseListener {
            override fun onCityCliocked(city: City) {
                parentFragmentManager.setFragmentResult(CITY_CODE, bundleOf(CITY_DATA to city))
                Log.d("MyLog", "City: {$city}")
                findNavController().popBackStack()
            }
        })

        recyclerview.adapter = adapter

        cityChooseFragmentViewModel.apiData.observe(viewLifecycleOwner, Observer {
            val listCity = mutableListOf<City>()
            it.forEach {
                listCity.add(City(it.localizedName, it.country.localizedName, it.key, it.country.iD))
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
                    if (s.isNotEmpty()){
                        cityTextChanged(s)
                    }
                }

            }
        })
    }

    fun cityTextChanged(s: CharSequence) {
        if (context?.let { checkForInternet(it) } == true){
            cityChooseFragmentViewModel.cityTextChanged(s)
        } else{
            showToast("Oops, something went wrong, please, check your Internet connection and try again", 1)
        }

    }

    companion object {
        const val CITY_CODE = "CODE_BACK_CITY"
        const val CITY_DATA = "CITY_DATA"
    }
}

