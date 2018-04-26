package com.example.ivan.simplinictest.mvp.repository.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City() :Parcelable {
    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("label")
    @Expose
    private var label: String? = null
    @SerializedName("countPeople")
    @Expose
    private var countPeople: Int? = null
    @SerializedName("countHostel")
    @Expose
    private var countHostel: Int? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        label = parcel.readString()
        countPeople = parcel.readValue(Int::class.java.classLoader) as? Int
        countHostel = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getLabel(): String? {
        return label
    }

    fun setLabel(label: String) {
        this.label = label
    }

    fun getCountPeople(): Int? {
        return countPeople
    }

    fun getCountHostel(): Int? {
        return countHostel
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(label)
        parcel.writeValue(countPeople)
        parcel.writeValue(countHostel)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }

}