package com.example.ivan.simplinictest.mvp.repository

import android.content.Context
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import rx.Observable


class DataModel(context: Context) {

    private val cache = Cache(context)
    val repository: CacheRepository = CacheRepository(cache)
    private val repositoryNetwork:NetworkRepository = NetworkRepository()

    fun getListCity(cacheFlag:Boolean):Observable<List<City>> {
        return if(cacheFlag){
            repository
                    .getListCity().concatMap {
                        if (it.isEmpty())
                             repositoryNetwork
                                     .getListCity()
                                     .map {
                                            cache.saveCities(it,!cacheFlag)
                                        it
                                     }
                        else
                            Observable.just(it)
                    }
        }else{
            repositoryNetwork.getListCity().map {
                cache.saveCities(it,!cacheFlag)
                it
            }
        }
    }

    fun getListHostel(cacheFlag:Boolean,city: Int?):Observable<List<Hostel>>{
        return if(cacheFlag){
            repository.getListHostel(city).concatMap {
                if(it.isEmpty())
                    repositoryNetwork
                            .getListHostel(city)
                            .map{
                             cache.saveHostels(it,!cacheFlag)
                            it
                }
                else Observable.just(it)
            }
        }else{
            repositoryNetwork.getListHostel(city).map {
                cache.saveHostels(it,cacheFlag)
                it
            }
        }
    }

    fun getListHostel(cacheFlag:Boolean): Observable<List<Hostel>>? {
        return if(cacheFlag){
            repository.getListHostel().concatMap {
                if(it.isEmpty())
                    repositoryNetwork
                            .getListHostel()
                            .map{
                                cache.saveHostels(it,!cacheFlag)
                                it
                            }
                else Observable.just(it)
            }
        }else{
            repositoryNetwork.getListHostel().map {
                cache.saveHostels(it,cacheFlag)
                it
            }
        }
    }
}