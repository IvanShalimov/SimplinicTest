package com.example.ivan.simplinictest.mvp.repository

import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import rx.Observable

interface Repository {

    fun getListCity():Observable<List<City>>

    fun getListHostel(city:Int?):Observable<List<Hostel>>

    fun getListHostel():Observable<List<Hostel>>

}