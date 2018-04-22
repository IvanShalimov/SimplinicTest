package com.example.ivan.simplinictest.mvp.repository


class DataModel {

    val repositoryNetwork:NetworkRepository = NetworkRepository()
   // val repository:CashRepository = CashRepository()
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