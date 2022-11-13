package com.hipolabs.universities.viewmodel

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hipolabs.universities.api.RepositoryCallback
import com.hipolabs.universities.databinding.ActivityMainBinding
import com.hipolabs.universities.model.University
import com.hipolabs.universities.repository.Repository

class MainActivityViewModel : ViewModel() {
    var universities: MutableLiveData<ArrayList<University>> =
        MutableLiveData<ArrayList<University>>()
    var error: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    val loading = MutableLiveData<Boolean>()

    fun getUniversities(country: String) {
        loading.postValue(true)
        if (country == "") {
            error.setValue(true)
        } else {
            error.setValue(false)
            Repository.instance.getUniversities(country,
                object : RepositoryCallback<java.util.ArrayList<University>> {
                    override fun onSuccess(data: java.util.ArrayList<University>?) {
                        loading.postValue(false)

                        universities.setValue(data)
                        Log.e("ViewModel", universities.value.toString())
                    }

                    override fun onError() {
                        loading.postValue(false)
                        Log.e("ViewModel", "error")
                        error.value = true
                    }
                })
        }
    }

}