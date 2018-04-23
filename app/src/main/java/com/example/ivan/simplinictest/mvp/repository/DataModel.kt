package com.example.ivan.simplinictest.mvp.repository

import android.content.Context
import com.example.ivan.simplinictest.mvp.repository.model.City
import rx.Observable


class DataModel(context: Context) {

    private val repositoryNetwork:NetworkRepository = NetworkRepository(Cashe(context))
    val repository:CashRepository = CashRepository(Cashe(context))


    fun getListCity(offline:Boolean,callback: Repository.ResponseCallback) {
        val observable = Observable
                .just(ArrayList<City>())

        if(offline){
            repository.callback = callback
            repository.getListCity()
        }else{
            repositoryNetwork.callback = callback
            repositoryNetwork.getListCity()
        }

    }

    fun getListHostel(offline:Boolean,city: Int?,
                      callback:Repository.ResponseCallback){
        if(offline){
            repository.callback = callback
            repository.getListHostel(city)
        }else{
            repositoryNetwork.callback = callback
            repositoryNetwork.getListHostel(city)
        }

    }
}