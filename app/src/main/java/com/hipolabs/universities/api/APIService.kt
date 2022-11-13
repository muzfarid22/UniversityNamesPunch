package com.hipolabs.universities.api

import com.hipolabs.universities.model.University
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("{search}")
    fun getUniversities(
        @Path("search") search: String?,
        @Query("country") country: String?
    ):Call<ArrayList<University>>
}