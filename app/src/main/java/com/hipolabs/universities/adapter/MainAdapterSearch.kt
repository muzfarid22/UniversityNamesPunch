package com.hipolabs.universities.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hipolabs.universities.databinding.AdapterUniversityLayoutBinding
import com.hipolabs.universities.model.University

class MainAdapterSearch: RecyclerView.Adapter<MainViewHolderSearch>() {

    var universityList = mutableListOf<University>()
    var links: List<String>? = null

    fun setMovieList(universityList: List<University>) {
        this.universityList = universityList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolderSearch {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterUniversityLayoutBinding.inflate(inflater, parent, false)
        return MainViewHolderSearch(binding)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MainViewHolderSearch, position: Int) {
        val movie = universityList[position]
        links = movie.webPages
        holder.binding.name.text = movie.name
        holder.binding.countryName.text = movie.country
        for (item in movie.webPages!!)
        holder.binding.linkName.text = item

    }

    override fun getItemCount(): Int {
        return universityList.size
    }
}

class MainViewHolderSearch(val binding: AdapterUniversityLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

}