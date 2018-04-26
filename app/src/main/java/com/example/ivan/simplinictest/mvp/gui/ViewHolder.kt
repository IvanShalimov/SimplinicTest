package com.example.ivan.simplinictest.mvp.gui

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.ivan.simplinictest.R

class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    //города
    var city: TextView? = itemView?.findViewById(R.id.city_name_text)
    var peoples: TextView? = itemView?.findViewById(R.id.people_count_text)
    var hotels: TextView? = itemView?.findViewById(R.id.hostel_count_text)
    var cityItem: CardView? = itemView?.findViewById(R.id.city_item)

    //отели
    var hostel: TextView? = itemView?.findViewById(R.id.hostel_text)
    var rate: hyogeun.github.com.colorratingbarlib.ColorRatingBar? = itemView?.findViewById(R.id.rate_text)
    var about: TextView? = itemView?.findViewById(R.id.about_text)
    var hostelItem: CardView? = itemView?.findViewById(R.id.hostel_item)
}