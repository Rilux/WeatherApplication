package com.example.weather.ui.oneDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.twelveHoursForecastDataResponse.ForecastItem
import com.example.weather.data.model.twelveHoursForecastDataResponse.TwelveHoursForecastDataResponse
import com.example.weather.databinding.ItemTwelveHoursForecastDataBinding
import kotlinx.android.synthetic.main.item_twelve_hours_forecast_data.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OneDayFragmentAdapter() :
    ListAdapter<ForecastItem, OneDayFragmentAdapter.RootFragmentViewHolder>(Comparator()) {

    private var listWeather = emptyList<ForecastItem>()

    class RootFragmentViewHolder(binding: ItemTwelveHoursForecastDataBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RootFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTwelveHoursForecastDataBinding.inflate(inflater, parent, false)
        return RootFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RootFragmentViewHolder, position: Int) {
        val weather = listWeather[position]

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(weather.dtTxt, pattern)

        holder.itemView.dataTextView.text =
            localDateTime.toLocalDate().toString() + "\n" + localDateTime.toLocalTime().toString()
        holder.itemView.temperatureTextView.text =
            Math.round(weather.main.temp).toString() + "°C"
        when(weather.weather[0].icon){
            "01d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__01d)
            "01n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__01n)
            "02d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__02d)
            "02n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__02n)
            "03d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__03)
            "03n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__03)
            "04d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__03)
            "04n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__03)
            "09d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__09)
            "09n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__09)
            "10d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__10)
            "10n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__10n)
            "11d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__11)
            "11n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__11)
            "13d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__13)
            "13n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__13)
            "50d" -> holder.itemView.imageView.setImageResource(R.drawable.ic__50)
            "50n" -> holder.itemView.imageView.setImageResource(R.drawable.ic__50)
        }
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