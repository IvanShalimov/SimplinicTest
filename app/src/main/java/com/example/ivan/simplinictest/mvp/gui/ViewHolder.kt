package com.example.ivan.simplinictest.mvp.gui

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ivan.simplinictest.R

class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    //города
    var city:TextView? = null
    var peoples:TextView? = null
    var hotels:TextView? = null
    var cityItem:CardView? = null

    //отели
    var hostel:TextView? = null
    var rate:hyogeun.github.com.colorratingbarlib.ColorRatingBar? = null
    var about:TextView? = null
    var hostelItem:CardView? = null

    init {
        when(itemView?.tag as Int){
            ListAdapter.CITIES ->{
                city = itemView?.findViewById(R.id.city_name_text)
                peoples= itemView?.findViewById(R.id.people_count_text)
                hotels = itemView?.findViewById(R.id.hostel_count_text)
                cityItem = itemView?.findViewById(R.id.city_item)
            }
            ListAdapter.HOSTEL ->{
                hostel = itemView?.findViewById(R.id.hostel_text)
                rate = itemView?.findViewById(R.id.rate_text)
                about = itemView?.findViewById(R.id.about_text)
                hostelItem = itemView?.findViewById(R.id.hostel_item)
            }
        }
    }
}