package com.example.ivan.simplinictest.mvp.gui

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ivan.simplinictest.R
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel

class ListAdapter: RecyclerView.Adapter<ViewHolder>() {

    companion object {
        val CITIES:Int = 0
        val HOSTEL:Int = 1
    }

    var list = ArrayList<Any>()
    var callback:Callback? = null
    var typListData = CITIES

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View
        if(typListData == CITIES){
            view = LayoutInflater.from(parent.context).inflate(R.layout.city_list_item,parent,false)
            view.tag = CITIES
        } else {
            view = LayoutInflater.from(parent.context).inflate(R.layout.hostel_list_item,parent,false)
            view.tag = HOSTEL
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(typListData == CITIES){
           val item =  list[position] as City
            holder.city?.text = item.getLabel()
            holder.peoples?.text = "${item.getCountPeople()}"
            holder.hotels?.text ="${item.getCountHostel()}"
            holder.cityItem?.setOnClickListener {
                callback?.onSelectItem(item)
            }
        } else {
            val item =  list[position] as Hostel
            holder.hostel?.text = item.getLabel()
            holder.rate?.text = "${item.getRate()}"
            holder.about?.text =""
        }
    }


    fun setData(parameter:Any?,type:Int){
        list = parameter as ArrayList<Any>
        changeData(type)
    }
    private fun changeData(type:Int){
        if (type == CITIES){
            typListData = CITIES
        } else {
            typListData = HOSTEL
        }

        notifyDataSetChanged()

    }

    interface Callback{
        fun onSelectItem(city:City)
    }
}