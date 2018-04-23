package com.example.ivan.simplinictest.mvp.gui

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SwitchCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.CompoundButton
import com.example.ivan.simplinictest.R
import com.example.ivan.simplinictest.mvp.ListPresenter
import com.example.ivan.simplinictest.mvp.ListViewState
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : MvpViewStateActivity<ListView, ListPresenter, ListViewState>(),
        SwipeRefreshLayout.OnRefreshListener,ListView,ListAdapter.Callback,
        CompoundButton.OnCheckedChangeListener {

    override fun setTitleCity(string:String) {
        toolbar.title = "${getString(R.string.app_name)}:$string"
    }

    private lateinit var adapter: ListAdapter
    private lateinit var listSwitch:SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        adapter = ListAdapter()
        adapter.callback = this
        list.adapter = adapter
        swipe_layout.setOnRefreshListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val itemSwitch = menu.findItem(R.id.app_bar_switch)
        itemSwitch.setActionView(R.layout.switch_item)
        listSwitch = menu
                .findItem(R.id.app_bar_switch)
                .actionView
                .findViewById(R.id.switch_button) as SwitchCompat

        if(viewState.currentViewState == ListViewState.SHOW_LIST_HOSTEL){
            listSwitch.isChecked = true
        }

        listSwitch.setOnCheckedChangeListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                if(viewState.currentViewState == 1){
                    refreshData(0,false)
                } else if(viewState.currentViewState == 2){
                    refreshData(1,false)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onRefresh() {
        if(viewState.currentViewState == 1){
            refreshData(0,false)
        } else if(viewState.currentViewState == 2){
            refreshData(1,false)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if(isChecked){
            //hotels
            viewState.currentViewState = ListViewState.SHOW_LIST_HOSTEL
            refreshData(1,true)
        } else {
            //cities
            viewState.currentViewState = ListViewState.SHOW_LIST_CITIES
            refreshData(0,true)
        }
    }

    override fun lockScreen(flag: Boolean) {
        swipe_layout.isRefreshing = flag
    }

    override fun onSelectItem(city: City) {
        presenter.selectedCity = city.getId()
        viewState.selectedCity = city.getId()
        viewState.cityName = city.getLabel()
        setTitleCity(city.getLabel()!!)
    }

    override fun setData(data: Any?,type:Int) {
        viewState.currentViewState = type
        adapter.setData(data,type)
        list.adapter = adapter
        viewState.currentViewState = type+1
    }

    override fun onNewViewStateInstance() {
        setTitleCity("Moscow")
        refreshData(0,true)
    }

    override fun createPresenter(): ListPresenter {
        return ListPresenter(applicationContext)
    }

    override fun createViewState(): ListViewState {
        return ListViewState()
    }

    override fun refreshData(flag:Int,cache:Boolean) {
        if(flag == 0){
            presenter.loadCity(cache)
        } else if(flag == 1){
            presenter.loadHostel(cache)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
