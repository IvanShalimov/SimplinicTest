package com.example.ivan.simplinictest.mvp

import android.content.Context
import com.example.ivan.simplinictest.mvp.gui.ListView
import com.example.ivan.simplinictest.mvp.repository.DataModel
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ListPresenter(context: Context): MvpBasePresenter<ListView>(){
    var repository:DataModel? = null

    private var cityLoad = false
    private var hostelLoad = false
    private var allHostelLoad = false

    private var subscription:Subscription? = null
    var selectedCity:Int? = 2

    init {
        repository = DataModel(context)
    }

   private fun onSuccess(response: Any) {
       var returnType = ListViewState.SHOW_LIST_CITIES

       if(cityLoad){
           returnType = ListViewState.SHOW_LIST_CITIES
           cityLoad = false
       }

       if(hostelLoad){
           returnType = ListViewState.SHOW_LIST_HOSTEL
           hostelLoad = false
       }

       if(allHostelLoad){
           returnType = ListViewState.SHOW_LIST_ALL_HOSTEL
           allHostelLoad = false
       }

       view.lockScreen(false)
       view?.setData(response,returnType)
    }

    fun loadCity(cash:Boolean){
        view.lockScreen(!cash)
        cityLoad = true
        subscription = repository?.getListCity(cash)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    onSuccess(it)
                },{
                    view?.showError("Error load city")
                })
    }

    fun loadHostel(cash:Boolean){
        view.lockScreen(!cash)
        hostelLoad = true
        subscription = repository?.getListHostel(cash,selectedCity)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    onSuccess(it)
                },{
                    view?.showError("Error load hostels for city")
                })
    }

    fun loadAllHostel(cash:Boolean){
        view.lockScreen(!cash)
        allHostelLoad = true
        subscription = repository?.getListHostel(cash)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    onSuccess(it)
                },{
                    view?.showError("Error load hostels")
                })
    }

    fun onDestroy(){
        subscription?.unsubscribe()
    }
}