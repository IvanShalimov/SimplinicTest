package com.example.ivan.simplinictest.mvp.repository

import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import rx.Observable

class CacheRepository(private val cache: Cache) : Repository {

    override fun getListCity():Observable<List<City>> {
        val onSubscribe = Observable.OnSubscribe<List<City>> { subscriber ->
            subscriber.onNext(cache.getCities())
            subscriber.onCompleted()
        }

        return Observable.create(onSubscribe)
    }

    override fun getListHostel(city: Int?):Observable<List<Hostel>> {
        val onSubscribe = Observable.OnSubscribe<List<Hostel>> { subscriber ->
            subscriber.onNext(cache.getHostels(city))
            subscriber.onCompleted()
        }

        return Observable.create(onSubscribe)
    }

    override fun getListHostel(): Observable<List<Hostel>> {
        val onSubscribe = Observable.OnSubscribe<List<Hostel>> { subscriber ->
            subscriber.onNext(cache.getHostels())
            subscriber.onCompleted()
        }

        return Observable.create(onSubscribe)
    }
}