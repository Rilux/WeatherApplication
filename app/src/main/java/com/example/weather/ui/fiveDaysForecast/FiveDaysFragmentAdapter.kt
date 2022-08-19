package com.example.weather.ui.fiveDaysForecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.DataForFiveDaysWeatherForecast
import com.example.weather.databinding.ItemFiveDaysForecastBinding
import kotlinx.android.synthetic.main.item_five_days_forecast.view.*
import kotlinx.android.synthetic.main.item_twelve_hours_forecast_data.view.*

class FiveDaysFragmentAdapter() :
    ListAdapter<DataForFiveDaysWeatherForecast, FiveDaysFragmentAdapter.FiveDaysFragmentViewHolder>(
        Comparator()
    ) {

    private var listWeather = emptyList<DataForFiveDaysWeatherForecast>()

    class FiveDaysFragmentViewHolder(binding: ItemFiveDaysForecastBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiveDaysFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFiveDaysForecastBinding.inflate(inflater, parent, false)
        return FiveDaysFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FiveDaysFragmentViewHolder, position: Int) {
        val weather = listWeather[position]

        holder.itemView.textViewTime.text = weather.dateTxt
        holder.itemView.textViewMaxTemp.text = Math.round(weather.tempMax).toString() + "°C"
        holder.itemView.textViewMinTemp.text = Math.round(weather.tempMin).toString() + "°C"
    }

    override fun getItemCount(): Int {
        return listWeather.count()
    }

    fun setList(list: List<DataForFiveDaysWeatherForecast>) {
        listWeather = list
        notifyDataSetChanged()
    }

    class Comparator : DiffUtil.ItemCallback<DataForFiveDaysWeatherForecast>() {
        override fun areItemsTheSame(
            oldItem: DataForFiveDaysWeatherForecast,
            newItem: DataForFiveDaysWeatherForecast
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: DataForFiveDaysWeatherForecast,
            newItem: DataForFiveDaysWeatherForecast
        ): Boolean {
            return oldItem == newItem
        }

    }


}