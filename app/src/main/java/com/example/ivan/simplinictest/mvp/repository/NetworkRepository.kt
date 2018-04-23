package com.example.ivan.simplinictest.mvp.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NetworkRepository(val cashe:Cashe) : Repository {

    private val BASE_URL = "http://azition.pro/"
    private var apiService:APIInterface? = null
    var callback: Repository.ResponseCallback? = null

    override fun getListCity() {
        val call = apiService?.getCities()
        call?.enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                cashe.saveCities(response.body()!!,false)
                callback?.onSuccess(response.body() as Any)
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                callback?.onError(t)
            }
        })
    }

    override fun getListHostel(city: Int?) {
        val call = apiService?.getListHostel(city)
        call?.enqueue(object : Callback<List<Hostel>> {
            override fun onResponse(call: Call<List<Hostel>>, response: Response<List<Hostel>>) {
                cashe.saveHostels(response.body() !!,false)
                callback?.onSuccess(response.body() as Any)
            }

            override fun onFailure(call: Call<List<Hostel>>, t: Throwable) {
                // Log error here since request failed
                callback?.onError(t)
            }
        })
    }

    init {
       val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        apiService = retrofit?.create(APIInterface::class.java)
    }


}

