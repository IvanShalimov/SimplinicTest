package com.example.ivan.simplinictest.mvp.gui

import com.hannesdorfmann.mosby3.mvp.MvpView

interface ListView: MvpView {

    fun setData(data:Any?,typeData:Int)

    fun lockScreen(flag:Boolean)

    fun refreshData(flag:Int,forceNetwork:Boolean)

    /**Crutch*/
    fun loadAllHostel()

    fun setTitleCity(string:String)
}