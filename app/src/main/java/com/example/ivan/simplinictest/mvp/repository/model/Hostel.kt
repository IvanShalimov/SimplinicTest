package com.example.ivan.simplinictest.mvp.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Hostel {

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
}