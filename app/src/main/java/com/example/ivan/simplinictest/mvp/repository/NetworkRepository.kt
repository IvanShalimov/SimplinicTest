package com.example.ivan.simplinictest.mvp.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import rx.Observable


class NetworkRepository : Repository {

    private val BASE_URL = "http://azition.pro/"
    private var apiService:APIInterface? = null

    override fun getListCity(): Observable<List<City>> {

        val onSubscribe = Observable.OnSubscribe<List<City>> { subscriber ->
            val call = apiService?.getCities()
            subscriber.onNext(call?.execute()?.body())
            subscriber.onCompleted()
        }

        return Observable.create(onSubscribe)
    }

    override fun getListHostel(city: Int?):Observable<List<Hostel>> {

        val onSubscribe = Observable.OnSubscribe<List<Hostel>> { subscriber ->
            val call = apiService?.getListHostel(city)
            subscriber.onNext(call?.execute()?.body())
            subscriber.onCompleted()
        }

        return Observable.create(onSubscribe)
    }

    init {
       val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

        apiService = retrofit?.create(APIInterface::class.java)
    }


}

