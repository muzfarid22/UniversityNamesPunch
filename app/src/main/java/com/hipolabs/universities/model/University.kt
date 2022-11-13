package com.hipolabs.universities.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcelable.Creator
import com.hipolabs.universities.model.University

class University protected constructor(`in`: Parcel) : Parcelable {
    val country: String?

    @SerializedName("web_pages")
    val webPages: List<String>?
    val name: String?
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(country)
        parcel.writeStringList(webPages)
        parcel.writeString(name)
    }

    init {
        country = `in`.readString()
        webPages = `in`.createStringArrayList()
        name = `in`.readString()
    }

    companion object CREATOR : Creator<University> {
        override fun createFromParcel(parcel: Parcel): University {
            return University(parcel)
        }

        override fun newArray(size: Int): Array<University?> {
            return arrayOfNulls(size)
        }
    }
}