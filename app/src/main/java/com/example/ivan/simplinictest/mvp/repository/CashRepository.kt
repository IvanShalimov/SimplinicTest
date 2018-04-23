package com.example.ivan.simplinictest.mvp.repository

class CashRepository(private val cashe: Cashe) : Repository {

    var callback: Repository.ResponseCallback? = null

    override fun getListCity() {
        callback?.onSuccess(cashe.getCities())
    }

    override fun getListHostel(city: Int?) {
        callback?.onSuccess(cashe.getHostels(city))

    }
}