package com.example.ivan.simplinictest.mvp.repository

import android.content.Context


class DataModel(context: Context) {

    val repositoryNetwork:NetworkRepository = NetworkRepository(Cashe(context))
    val repository:CashRepository = CashRepository()
    var fromCash = false


    fun getListCity(callback: NetworkRepository.ResponseCallback) {
        repositoryNetwork.callback = callback
        repositoryNetwork.getListCity()
    }

    fun getListHostel(city: Int?,callback: NetworkRepository.ResponseCallback){
        repositoryNetwork.callback = callback
        repositoryNetwork.getListHostel(city)
    }
}