package com.hipolabs.universities.api

import com.hipolabs.universities.model.University
import java.util.ArrayList

interface RepositoryCallback<T> {
    fun onSuccess(data: ArrayList<University>?)
    fun onError()
}