package com.example.ivan.simplinictest.mvp.gui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.ivan.simplinictest.R
import com.example.ivan.simplinictest.mvp.ListPresenter
import com.example.ivan.simplinictest.mvp.ListViewState
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : MvpViewStateActivity<ListView, ListPresenter, ListViewState>(),
        SwipeRefreshLayout.OnRefreshListener,ListView,ListAdapter.Callback {
    override fun lockScreen(flag: Boolean) {
        swipe_layout.isRefreshing = flag
    }

    override fun onSelectItem(id: Int?) {
        presenter.selectedCity = id
    }

    override fun setData(data: Any?,type:Int) {
        adapter.setData(data,type)
        list.adapter = adapter
    }

    override fun onNewViewStateInstance() {

    }

    override fun createPresenter(): ListPresenter {
        return ListPresenter()
    }

    override fun createViewState(): ListViewState {
        return ListViewState()
    }


    lateinit var adapter: ListAdapter
    lateinit var layoutManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        adapter = ListAdapter()
        adapter.callback = this
        list.adapter = adapter
        swipe_layout.setOnRefreshListener(this)

        presenter.loadData(false)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val itemSwitch = menu.findItem(R.id.app_bar_switch)
        itemSwitch.setActionView(R.layout.switch_item)
        val switch = menu
                .findItem(R.id.app_bar_switch)
                .actionView
                .findViewById(R.id.switch_button) as SwitchCompat
        switch.setOnCheckedChangeListener({ buttonView, isChecked ->
            if(isChecked){
                //hotels
                Log.d("Test","hostel")
                //adapter.changeData(ListAdapter.HOSTEL)
                presenter.loadData(false,0)
            } else {
                //cities
                Log.d("Test","cities")
                presenter.loadData(false)
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {

                true
            }
/*            R.id.app_bar_switch ->{
                val a = item.actionView as SwitchCompat
                Log.d("Test","checked = "+a.isChecked)
                true
            }*/
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRefresh() {
        Log.d("Test","onRefresh")
    }
}
