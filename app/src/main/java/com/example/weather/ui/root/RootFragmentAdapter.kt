package com.example.weather.ui.root

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.twelveHoursForecastDataResponse.ForecastItem
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import com.example.weather.databinding.ItemFiveDayWeatherDataBinding
import kotlinx.android.synthetic.main.item_five_day_weather_data.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RootFragmentAdapter() :
    ListAdapter<ForecastItem, RootFragmentAdapter.RootFragmentViewHolder>(Comparator()) {

    private var listWeather = emptyList<ForecastItem>()

    class RootFragmentViewHolder(binding: ItemFiveDayWeatherDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFiveDayWeatherDataBinding.inflate(inflater, parent, false)
        return RootFragmentViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RootFragmentViewHolder, position: Int) {
        val weather = listWeather[position]

        //val parsedDate = LocalDateTime.parse(weather.dtTxt, DateTimeFormatter.ISO_DATE_TIME)
        //val formattedDate = parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy \n HH:mm"))

        holder.itemView.dataTextView.text = weather.dtTxt
        holder.itemView.temperatureTextView.text =
            Math.round(weather.main.temp).toString() + "°C"
    }

    override fun getItemCount(): Int {
        return listWeather.size
    }

    fun setList(list: TwelveHoursForecastDataResponse) {
        listWeather = list.list
        notifyDataSetChanged()
    }

    class Comparator : DiffUtil.ItemCallback<ForecastItem>() {
        override fun areItemsTheSame(
            oldItem: ForecastItem,
            newItem: ForecastItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ForecastItem,
            newItem: ForecastItem
        ): Boolean {
            return oldItem == newItem
        }

    }
}