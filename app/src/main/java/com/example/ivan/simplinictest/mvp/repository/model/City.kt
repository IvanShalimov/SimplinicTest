package com.example.ivan.simplinictest.mvp.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class City {
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

    fun setCountPeople(countPeople: Int?) {
        this.countPeople = countPeople
    }

    fun getCountHostel(): Int? {
        return countHostel
    }

    fun setCountHostel(countHostel: Int?) {
        this.countHostel = countHostel
    }
}