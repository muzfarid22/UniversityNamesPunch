package com.hipolabs.universities.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hipolabs.universities.databinding.CountryNameLayoutBinding
import com.hipolabs.universities.model.CountryName


class CountryNameAdapter(
    private val countryNameList: ArrayList<CountryName>,
    private val listener: (CountryName, Int) -> Unit
) :
    RecyclerView.Adapter<CountryNameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = CountryNameLayoutBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(countryNameList[position])
        holder.itemView.setOnClickListener { listener(countryNameList[position], position) }
    }

    override fun getItemCount(): Int {
        return countryNameList.size
    }

    class ViewHolder(var binding: CountryNameLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(hero: CountryName) {
            binding.image.setImageResource(hero.image)
            binding.name.text = hero.name
        }
    }
}
