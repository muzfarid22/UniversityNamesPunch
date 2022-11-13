package com.hipolabs.universities.repository
import android.util.Log
import com.hipolabs.universities.api.APIService
import com.hipolabs.universities.api.RepositoryCallback
import com.hipolabs.universities.model.University
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

class Repository private constructor() {
    var universities: ArrayList<University>? = null
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService: APIService = retrofit.create(APIService::class.java)
    fun getUniversities(
        country: String?,
        repositoryCallback: RepositoryCallback<ArrayList<University>>
    ): ArrayList<University>? {
        val call: Call<ArrayList<University>> = apiService.getUniversities(SEARCH, country)
        call.enqueue(object : Callback<ArrayList<University>> {

            override fun onResponse(
                call: Call<ArrayList<University>?>,
                response: Response<ArrayList<University>?>
            ) {
                if (!response.isSuccessful) {
                    repositoryCallback.onError()
                } else {
                    if (response.body()!!.isEmpty()) {
                        repositoryCallback.onError()
                    } else {
                        universities = response.body()
                        repositoryCallback.onSuccess(universities)
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<University>?>, t: Throwable) {
                Log.e("error", t.toString())
                repositoryCallback.onError()
            }
        })
        return universities
    }

    companion object {
        val instance = Repository()
        private const val BASE_URL = "http://universities.hipolabs.com/"
        private const val SEARCH = "search"
    }
}