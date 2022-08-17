package com.example.weather.ui.cityChoose

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.data.model.City
import com.example.weather.databinding.ItemCityBinding
import kotlinx.android.synthetic.main.item_city.view.*

interface CityChooseListener {
    fun onCityClicked(city: City)
}

class CityChooseAdapter(
    private val actionListener: CityChooseListener
) :
    ListAdapter<City, CityChooseAdapter.CityChooseViewHolder>(Comparator()), View.OnClickListener {
    var listCityChoose = emptyList<City>()

    class CityChooseViewHolder(binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onClick(v: View) {
        val city = v.tag as City
        actionListener.onCityClicked(city)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityChooseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCityBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)

        return CityChooseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityChooseViewHolder, position: Int) {
        val city = listCityChoose[position]
        holder.itemView.city_text_view.text =
            listCityChoose[position].city + ", " + listCityChoose[position].country
        holder.itemView.tag = city
    }

    override fun getItemCount(): Int {
        return listCityChoose.size
    }

    fun setList(list: List<City>) {
        listCityChoose = list
        notifyDataSetChanged()
    }

    class Comparator : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.cityKey == newItem.cityKey
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.cityKey == newItem.cityKey
        }

    }
}