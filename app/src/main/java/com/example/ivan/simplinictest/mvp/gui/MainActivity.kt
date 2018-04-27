package com.example.ivan.simplinictest.mvp.gui

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SwitchCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.CompoundButton
import android.widget.Toast
import com.example.ivan.simplinictest.R
import com.example.ivan.simplinictest.mvp.ListPresenter
import com.example.ivan.simplinictest.mvp.ListViewState
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_list_item.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : MvpViewStateActivity<ListView, ListPresenter, ListViewState>(),
        SwipeRefreshLayout.OnRefreshListener,ListView,ListAdapter.Callback,
        CompoundButton.OnCheckedChangeListener {

    override fun setTitleCity(string:String?) {
        toolbar.title = "${getString(R.string.app_name)}$string"
    }

    private lateinit var listSwitch:SwitchCompat
    private var savedInstance:Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager

        savedInstance = savedInstanceState

        swipe_layout.setOnRefreshListener(this)
    }

    override fun restoreData(){
        if(viewState != null)
        if(viewState.currentViewState == ListViewState.SHOW_LIST_CITIES){
            val adapter = ListAdapter<City>()

            if (savedInstance == null){
                list.adapter = adapter
                refreshData(viewState.currentViewState,true)
            } else {
                adapter.setDataFromBundleCity(savedInstance)
                list.adapter = adapter

                list.layoutManager.onRestoreInstanceState(savedInstance?.
                        getParcelable<Parcelable>("state"))
            }

            adapter.callback = this
        } else {
            val adapter = ListAdapter<Hostel>()

            if (savedInstance == null){
                list.adapter = adapter
                refreshData(viewState.currentViewState,true)
            } else {
                adapter.setDataFromBundleCity(savedInstance)
                list.adapter = adapter

                list.layoutManager.onRestoreInstanceState(savedInstance?.
                        getParcelable<Parcelable>("state"))
            }

            adapter.callback = this
        }

        else {
            val adapter = ListAdapter<City>()
            list.adapter = adapter
            adapter.callback = this
            refreshData(ListViewState.SHOW_LIST_CITIES,true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val itemSwitch = menu.findItem(R.id.app_bar_switch)
        itemSwitch.setActionView(R.layout.switch_item)
        listSwitch = menu
                .findItem(R.id.app_bar_switch)
                .actionView
                .findViewById(R.id.switch_button) as SwitchCompat

        //restore checked button
        if(viewState.currentViewState == ListViewState.SHOW_LIST_ALL_HOSTEL){
            listSwitch.isChecked = true
        }

        //restore up button
        if(viewState.currentViewState == ListViewState.SHOW_LIST_HOSTEL){
            settingUpButton(true)
        }

        listSwitch.setOnCheckedChangeListener(this)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                reactOnRefresh()
                true
            }
            android.R.id.home ->{
                settingUpButton(false)
                setTitleCity("")
                refreshData(ListViewState.SHOW_LIST_CITIES,false)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun settingUpButton(flag:Boolean){
        supportActionBar?.setDisplayHomeAsUpEnabled(flag)
        supportActionBar?.setDisplayShowHomeEnabled(flag)
    }

    override fun onRefresh() {
        reactOnRefresh()
    }

    private fun reactOnRefresh(){
        when {
            viewState.currentViewState == ListViewState.SHOW_LIST_CITIES ->
                refreshData(ListViewState.SHOW_LIST_CITIES,false)
            viewState.currentViewState == ListViewState.SHOW_LIST_HOSTEL ->
                refreshData(ListViewState.SHOW_LIST_HOSTEL,false)
            viewState.currentViewState == ListViewState.SHOW_LIST_ALL_HOSTEL ->
                refreshData(ListViewState.SHOW_LIST_ALL_HOSTEL,false)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if(isChecked){
            //hotels
            viewState.currentViewState = ListViewState.SHOW_LIST_ALL_HOSTEL
            refreshData(ListViewState.SHOW_LIST_ALL_HOSTEL,true)
        } else {
            //cities
            viewState.currentViewState = ListViewState.SHOW_LIST_CITIES
            refreshData(ListViewState.SHOW_LIST_CITIES,true)
        }
    }

    override fun lockScreen(flag: Boolean) {
        swipe_layout.isRefreshing = flag
    }

    override fun onSelectItem(city: City) {
        //store variable
        presenter.selectedCity = city.getId()
        viewState.selectedCity = city.getId()
        viewState.cityName = city.getLabel()
        //ui
        setTitleCity(":${city.getLabel()}")
        settingUpButton(true)
        //request
        viewState.currentViewState = ListViewState.SHOW_LIST_HOSTEL
        refreshData(ListViewState.SHOW_LIST_HOSTEL,true)
    }

    override fun setData(data: Any?, typeData:Int) {

        val visibleItem = (list.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()

        viewState.currentViewState = typeData

        if(typeData > ListViewState.SHOW_LIST_CITIES){
            val adapter = ListAdapter<City>()
            adapter.setData(data as ArrayList<City>)
            list.adapter = adapter
        } else {
            val adapter = ListAdapter<Hostel>()
            adapter.setData(data as ArrayList<Hostel>)
            list.adapter = adapter
        }

        (list.layoutManager as LinearLayoutManager).scrollToPosition(visibleItem)
    }

    override fun onNewViewStateInstance() {
        refreshData(ListViewState.SHOW_LIST_CITIES,true)
    }

    override fun createPresenter(): ListPresenter {
        return ListPresenter(applicationContext)
    }

    override fun createViewState(): ListViewState {
        return ListViewState()
    }

    private fun refreshData(flag:Int, forceNetwork:Boolean) {
        when (flag) {
            ListViewState.SHOW_LIST_CITIES -> presenter.loadCity(forceNetwork)
            ListViewState.SHOW_LIST_HOSTEL -> presenter.loadHostel(forceNetwork)
            ListViewState.SHOW_LIST_ALL_HOSTEL -> presenter.loadAllHostel(forceNetwork)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun showError(errorText: String) {
        Toast.makeText(applicationContext,errorText,Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable("state",list.layoutManager.onSaveInstanceState())

        val adapter = if(viewState.currentViewState == ListViewState.SHOW_LIST_CITIES){
            list.adapter as ListAdapter<City>
        } else {
            list.adapter as ListAdapter<Hostel>
        }

        outState?.putParcelableArrayList("list", adapter.getData())

        super.onSaveInstanceState(outState)
    }

}
