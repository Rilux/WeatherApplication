package com.example.weather.ui.root

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.twelveHoursWeatherResponse.TwelveHoursDataResponse
import com.example.weather.data.model.twelveHoursWeatherResponse.TwelveHoursDataResponseItem
import com.example.weather.databinding.ItemFiveDayWeatherDataBinding
import kotlinx.android.synthetic.main.item_five_day_weather_data.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RootFragmentAdapter() :
    RecyclerView.Adapter<RootFragmentAdapter.RootFragmentViewHolder>() {

    var listWeather = emptyList<TwelveHoursDataResponseItem>()

    class RootFragmentViewHolder(binding: ItemFiveDayWeatherDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFiveDayWeatherDataBinding.inflate(inflater, parent, false)
        return RootFragmentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RootFragmentViewHolder, position: Int) {
        val weather = listWeather[position]

        val parsedDate = LocalDateTime.parse(weather.dateTime, DateTimeFormatter.ISO_DATE_TIME)
        val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy \n HH:mm"))

        holder.itemView.dataTextView.text = formattedDate
        holder.itemView.temperatureTextView.text =
            Math.round(weather.temperature.value).toString() + "°C"
    }

    override fun getItemCount(): Int {
        return listWeather.size
    }

    fun setList(list: TwelveHoursDataResponse) {
        listWeather = list
        notifyDataSetChanged()
    }
}