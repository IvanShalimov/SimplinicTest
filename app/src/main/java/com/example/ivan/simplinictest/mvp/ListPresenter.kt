package com.example.ivan.simplinictest.mvp

import android.content.Context
import com.example.ivan.simplinictest.mvp.gui.ListAdapter
import com.example.ivan.simplinictest.mvp.gui.ListView
import com.example.ivan.simplinictest.mvp.repository.DataModel
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListPresenter(context: Context): MvpBasePresenter<ListView>(){
    var repository:DataModel? = null
    var cityLoad = false
    var hostelLoad = false
    var subscription:Subscription? = null
    var selectedCity:Int? = 2

    init {
        repository = DataModel(context)
    }

   private fun onSuccess(response: Any) {
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

    fun loadCity(cash:Boolean){
        view.lockScreen(true)
        cityLoad = true
        subscription = repository?.getListCity(cash)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    onSuccess(it)
                })
    }

    fun loadHostel(cash:Boolean){
        view.lockScreen(true)
        hostelLoad = true
        subscription = repository?.getListHostel(cash,selectedCity)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    onSuccess(it)
                })
    }

    fun onDestroy(){
        subscription?.unsubscribe()
    }
}