package com.example.weather.ui.fiveDaysForecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.model.DataForFiveDaysWeatherForecast
import com.example.weather.databinding.ItemFiveDaysForecastBinding
import kotlinx.android.synthetic.main.item_five_days_forecast.view.*

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
        when(weather.image){
            "01d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__01d)
            "01n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__01n)
            "02d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__02d)
            "02n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__02n)
            "03d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__03)
            "03n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__03)
            "04d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__03)
            "04n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__03)
            "09d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__09)
            "09n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__09)
            "10d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__10)
            "10n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__10n)
            "11d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__11)
            "11n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__11)
            "13d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__13)
            "13n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__13)
            "50d" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__50)
            "50n" -> holder.itemView.imageView2.setImageResource(R.drawable.ic__50)
        }
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