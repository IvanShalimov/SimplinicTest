package com.example.ivan.simplinictest.mvp.repository.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Hostel() : Parcelable {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("idCity")
    @Expose
    private var idCity: Int? = null
    @SerializedName("label")
    @Expose
    private var label: String? = null
    @SerializedName("rate")
    @Expose
    private var rate: Int? = null
    @SerializedName("about")
    @Expose
    private var about: String? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        idCity = parcel.readValue(Int::class.java.classLoader) as? Int
        label = parcel.readString()
        rate = parcel.readValue(Int::class.java.classLoader) as? Int
        about = parcel.readString()
    }

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getIdCity(): Int? {
        return idCity
    }

    fun setIdCity(idCity: Int?) {
        this.idCity = idCity
    }

    fun getLabel(): String? {
        return label
    }

    fun setLabel(label: String) {
        this.label = label
    }

    fun getRate(): Int? {
        return rate
    }

    fun setRate(rate: Int?) {
        this.rate = rate
    }

    fun getAbout(): String? {
        return about
    }

    fun setAbout(about: String) {
        this.about = about
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeValue(idCity)
        parcel.writeString(label)
        parcel.writeValue(rate)
        parcel.writeString(about)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Hostel> {
        override fun createFromParcel(parcel: Parcel): Hostel {
            return Hostel(parcel)
        }

        override fun newArray(size: Int): Array<Hostel?> {
            return arrayOfNulls(size)
        }
    }
}