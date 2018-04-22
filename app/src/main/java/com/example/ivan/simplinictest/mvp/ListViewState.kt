package com.example.ivan.simplinictest.mvp

import android.os.Bundle
import com.example.ivan.simplinictest.mvp.gui.ListView
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState

class ListViewState: RestorableViewState<ListView> {
    override fun saveInstanceState(out: Bundle) {

    }

    override fun apply(view: ListView?, retained: Boolean) {

    }

    override fun restoreInstanceState(`in`: Bundle?): RestorableViewState<ListView> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}