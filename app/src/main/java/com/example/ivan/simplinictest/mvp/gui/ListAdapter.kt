package com.example.ivan.simplinictest.mvp.gui

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ivan.simplinictest.R
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import kotlinx.android.synthetic.main.content_main.*

class ListAdapter<T>: RecyclerView.Adapter<ViewHolder>() {

    var list = ArrayList<T>()
    var callback:Callback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            LayoutInflater.from(parent.context)
                    .inflate(R.layout.complex_adpter_item_layout
                            ,parent,
                            false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(list[position] is City){
            //hide-show card
            holder.hostelItem?.visibility = View.GONE
            holder.cityItem?.visibility = View.VISIBLE

            //setting need card
            val item =  list[position] as City
            holder.city?.text = item.getLabel()
            holder.peoples?.text = "${item.getCountPeople()}"
            holder.hotels?.text ="${item.getCountHostel()}"
            holder.cityItem?.setOnClickListener {
                callback?.onSelectItem(item)
            }
        } else {
            //hide-show card
            holder.hostelItem?.visibility = View.VISIBLE
            holder.cityItem?.visibility = View.GONE

            //setting need card
            val item =  list[position] as Hostel
            holder.hostel?.text = item.getLabel()
            holder.rate?.rating = item.getRate()!!.toFloat()
            holder.about?.text = ""
        }
    }

    fun setData(parameter:ArrayList<T>){
        list = parameter

        notifyDataSetChanged()
    }

    fun getData():ArrayList<T>{
        return list
    }

    interface Callback{
        fun onSelectItem(city:City)
    }

    fun setDataFromBundleCity(bundle:Bundle?){
        list = bundle?.getParcelableArrayList<City>("list") as ArrayList<T>

        notifyDataSetChanged()
    }

    fun setDataFromBundleHostel(bundle:Bundle?){
        list = bundle?.getParcelableArrayList<Hostel>("list") as ArrayList<T>

        notifyDataSetChanged()
    }

}