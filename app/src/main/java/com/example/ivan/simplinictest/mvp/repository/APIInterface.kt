package com.example.ivan.simplinictest.mvp.repository

import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface APIInterface {

    @Headers("Content-Type: application/json")
    @GET("cities")
    fun getCities(): Call<List<City>>

    @Headers("Content-Type: application/json")
    @GET("hostels/byCity/{id}")
    fun getListHostel(@Path("id") id: Int?): Call<List<Hostel>>

    @Headers("Content-Type: application/json")
    @GET("hostels")
    fun getListHostels(): Call<List<Hostel>>
}