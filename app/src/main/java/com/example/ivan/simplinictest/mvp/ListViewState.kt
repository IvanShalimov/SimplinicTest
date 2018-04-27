package com.example.ivan.simplinictest.mvp

import android.os.Bundle
import com.example.ivan.simplinictest.mvp.gui.ListView
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState

class ListViewState: RestorableViewState<ListView> {

    companion object {
        const val SHOW_LIST_CITIES = 1
        const val SHOW_LIST_HOSTEL = 2
        const val SHOW_LIST_ALL_HOSTEL = 3

        //key for save
        const val STATE = "state"
        const val SELECTED_CITY = "city"
        const val NAME_SELECTED_CITY = "name_city"
    }

    var currentViewState = 1
    var selectedCity:Int? = 2
    var cityName:String? ="Moscow"

    override fun saveInstanceState(out: Bundle) {
        out.putInt(STATE,currentViewState)
        out.putInt(SELECTED_CITY,selectedCity!!)
        out.putString(NAME_SELECTED_CITY,cityName!!)
    }

    override fun apply(view: ListView?, retained: Boolean) {

        when(currentViewState){
            SHOW_LIST_CITIES ->{
                view?.restoreData()
            }
            SHOW_LIST_HOSTEL ->{
                view?.setTitleCity(":$cityName")
                view?.restoreData()
            }
            SHOW_LIST_ALL_HOSTEL ->{
                view?.restoreData()
            }
        }

    }

    override fun restoreInstanceState(`in`: Bundle?): RestorableViewState<ListView> {
        if(`in`!= null){
            currentViewState = `in`.getInt(STATE)
            selectedCity = `in`.getInt(SELECTED_CITY)
            cityName = `in`.getString(NAME_SELECTED_CITY)
        }
        return this
    }
}