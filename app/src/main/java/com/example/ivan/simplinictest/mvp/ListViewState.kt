package com.example.ivan.simplinictest.mvp

import android.os.Bundle
import com.example.ivan.simplinictest.mvp.gui.ListView
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState

class ListViewState: RestorableViewState<ListView> {

    companion object {
        val LOADING = 0
        val SHOW_LIST_CITIES = 1
        val SHOW_LIST_HOSTEL = 2

        //key for save
        val STATE = "state"
    }

    var currentViewState = 0

    override fun saveInstanceState(out: Bundle) {
        out.putInt(STATE,currentViewState)
    }

    override fun apply(view: ListView?, retained: Boolean) {

        when(currentViewState){
            LOADING ->{

            }
            SHOW_LIST_CITIES ->{

            }
            SHOW_LIST_HOSTEL ->{

            }
        }
    }

    override fun restoreInstanceState(`in`: Bundle?): RestorableViewState<ListView> {
        if(`in`!= null){
            currentViewState = `in`.getInt(STATE)
        }
        return this
    }
}