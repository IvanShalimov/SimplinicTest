package com.example.ivan.simplinictest.mvp

import android.content.Context
import com.example.ivan.simplinictest.mvp.gui.ListAdapter
import com.example.ivan.simplinictest.mvp.gui.ListView
import com.example.ivan.simplinictest.mvp.repository.DataModel
import com.example.ivan.simplinictest.mvp.repository.NetworkRepository
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

class ListPresenter(context: Context): MvpBasePresenter<ListView>(), NetworkRepository.ResponseCallback {

    var cityLoad = false
    var hostelLoad = false

    var selectedCity:Int? = 2
    override fun onSuccess(response: Any) {
        var returnType = ListAdapter.CITIES
        if(cityLoad){
            returnType = ListAdapter.CITIES
            cityLoad = false
        }
        if(hostelLoad){
            returnType = ListAdapter.HOSTEL
            hostelLoad = false
        }
        view.lockScreen(false)
        view?.setData(response,returnType)
    }

    override fun onError(t: Throwable) {
        //stub
        view.lockScreen(false)
    }

    var repository:DataModel? = null

    init {
       repository = DataModel(context)
    }

    fun loadData(cash:Boolean){
        cityLoad = true
        view.lockScreen(true)
        repository?.fromCash  = cash
        repository?.getListCity(this)
    }

    fun loadData(cash:Boolean,id:Int){view.lockScreen(true)
        hostelLoad = true
        repository?.fromCash  = cash
        repository?.getListHostel(selectedCity,this)
    }
}