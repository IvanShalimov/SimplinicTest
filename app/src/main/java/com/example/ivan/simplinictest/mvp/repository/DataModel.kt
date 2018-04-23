package com.example.ivan.simplinictest.mvp.repository

import android.content.Context
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import rx.Observable


class DataModel(context: Context) {

    private val cache = Cache(context)
    val repository: CacheRepository = CacheRepository(cache)
    private val repositoryNetwork:NetworkRepository = NetworkRepository()

    fun getListCity(offline:Boolean,force:Boolean):Observable<List<City>> {
        return if(offline){
            repository.getListCity()
        }else{
            repositoryNetwork.getListCity().map {
                cache.saveCities(it,force)
                it
            }
        }
    }

    fun getListHostel(offline:Boolean,city: Int?,force:Boolean):Observable<List<Hostel>>{
        return if(offline){
            repository.getListHostel(city)
        }else{
            repositoryNetwork.getListHostel(city).map {
                cache.saveHostels(it,force)
                it
            }
        }
    }
}