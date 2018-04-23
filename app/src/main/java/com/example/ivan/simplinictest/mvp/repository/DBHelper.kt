package com.example.ivan.simplinictest.mvp.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):
        SQLiteOpenHelper(context,"database",null,1) {

    companion object {
        val TABLE_CITY = "city"
        val TABLE_PUBLIC_ID = "public_id"
        val TABLE_LABEL = "label"
        val TABLE_CITY_COUNT_PEOPLE = "count_people"
        val TABLE_CITY_COUNT_HOSTEL = "count_hostel"

        val TABLE_HOSTEL = "hostel"
        val TABLE_HOSTEL_CITY_ID = "city_id"
        val TABLE_HOSTEL_RATE = "rate"
        val TABLE_HOSTEL_ABOUT = "about"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table "+TABLE_CITY+" (" +
                "id integer primary key autoincrement," +
                ""+ TABLE_PUBLIC_ID +" integer," +
                ""+ TABLE_LABEL +" text," +
                ""+TABLE_CITY_COUNT_PEOPLE+" integer," +
                ""+TABLE_CITY_COUNT_HOSTEL+" integer" +
                ");")
        db?.execSQL("create table "+TABLE_HOSTEL+" (" +
                "id integer primary key autoincrement," +
                ""+ TABLE_PUBLIC_ID +" integer," +
                ""+ TABLE_LABEL +" text,"+
                ""+TABLE_HOSTEL_CITY_ID+" integer,"+
                ""+TABLE_HOSTEL_RATE+"integer," +
                ""+TABLE_HOSTEL_ABOUT+" text" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}