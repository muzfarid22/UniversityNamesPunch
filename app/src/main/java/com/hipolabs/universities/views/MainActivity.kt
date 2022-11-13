package com.hipolabs.universities.views

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hipolabs.universities.R
import com.hipolabs.universities.adapter.CountryNameAdapter
import com.hipolabs.universities.adapter.MainAdapterSearch
import com.hipolabs.universities.databinding.ActivityMainBinding
import com.hipolabs.universities.model.CountryName
import com.hipolabs.universities.viewmodel.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModelSearch: MainActivityViewModel
    val adapterSearch = MainAdapterSearch()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!isNetworkAvailable == true) {
            AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Internet Connection Alert")
                .setMessage("Please Check Your Internet Connection").setPositiveButton(
                    "Close"
                ) { dialogInterface, i -> finish() }.show()
        } else if (isNetworkAvailable == true) {
            Toast.makeText(
                this@MainActivity, "Welcome", Toast.LENGTH_LONG
            ).show()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModelSearch = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.recyclerview.adapter = adapterSearch

        viewModelSearch.universities.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapterSearch.setMovieList(it)
            adapterSearch.notifyDataSetChanged()
        })

        viewModelSearch.loading.observe(this, Observer {
            binding.showProgressbar.visibility = if (it) View.VISIBLE else View.GONE
        })
        setupRecyclerView()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        binding.recyclerviewName.apply {
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
            adapter = CountryNameAdapter(createHeroList()) { hero, position ->
                Toast.makeText(
                    this@MainActivity, "Clicked on : ${hero.name}", Toast.LENGTH_SHORT
                ).show()
                viewModelSearch.getUniversities(hero.name)
            }
        }
    }

    private fun createHeroList(): ArrayList<CountryName> {
        return arrayListOf<CountryName>(
            CountryName(
                "Pakistan", R.drawable.pakistan_icon
            ), CountryName(
                "India", R.drawable.india_icon
            ), CountryName(
                "Bangladesh", R.drawable.bangladesh_icon
            ), CountryName(
                "Canada", R.drawable.canada_icons
            ), CountryName(
                "Germany", R.drawable.germany_icon
            ), CountryName(
                "Sweden", R.drawable.sweden_icon
            ), CountryName(
                "Japan", R.drawable.japan_icon
            ), CountryName(
                "Australia", R.drawable.australia_icon
            ), CountryName(
                "United States", R.drawable.united_states_icon
            ), CountryName(
                "United Kingdom", R.drawable.united_kingdom_icon
            )

        )
    }

    val isNetworkAvailable: Boolean
        @SuppressLint("NewApi")
        get() {
            val connectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivityManager != null) {
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true
                    }

                }
            }
            return false
        }
}