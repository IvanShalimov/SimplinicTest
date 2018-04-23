package com.example.ivan.simplinictest.mvp.repository

interface Repository {

    fun getListCity()

    fun getListHostel(city:Int?)

    interface ResponseCallback{

        fun onSuccess(response:Any)
        fun onError(t:Throwable)
    }
}