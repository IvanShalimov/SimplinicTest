package com.example.ivan.simplinictest.mvp.repository

import android.content.Context
import com.example.ivan.simplinictest.mvp.repository.model.City
import com.example.ivan.simplinictest.mvp.repository.model.Hostel
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase


class Cashe(context: Context) {

    var helper: DBHelper = DBHelper(context)

    fun saveCities(cities: List<City>, force: Boolean) {

        val db = helper.writableDatabase

        for (item: City in cities) {
            val cv = ContentValues()
            cv.put(DBHelper.TABLE_PUBLIC_ID, item.getId())
            cv.put(DBHelper.TABLE_LABEL, item.getLabel())
            cv.put(DBHelper.TABLE_CITY_COUNT_PEOPLE, item.getCountPeople())
            cv.put(DBHelper.TABLE_CITY_COUNT_HOSTEL, item.getCountHostel())

            if (!findCity(item.getId()!!,db,DBHelper.TABLE_CITY)) {
                //insert
                db.insert(DBHelper.TABLE_CITY, null, cv)
            } else if (force) {
                //update
                db.update(DBHelper.TABLE_CITY, cv,
                        DBHelper.TABLE_PUBLIC_ID+" = ?",
                        arrayOf("${item.getId()}") )
            }
        }
        helper.close()
    }

    fun saveHostels(hostels: List<Hostel>, force: Boolean) {
        val db = helper.writableDatabase

        for (item: Hostel in hostels) {
            val cv = ContentValues()
            cv.put(DBHelper.TABLE_PUBLIC_ID, item.getId())
            cv.put(DBHelper.TABLE_LABEL, item.getLabel())
            cv.put(DBHelper.TABLE_HOSTEL_CITY_ID, item.getIdCity())
            cv.put(DBHelper.TABLE_HOSTEL_RATE, item.getRate())
            cv.put(DBHelper.TABLE_HOSTEL_ABOUT, item.getAbout())


            if (!findCity(item.getId()!!,db,DBHelper.TABLE_HOSTEL)) {
                //insert
                db.insert(DBHelper.TABLE_HOSTEL, null, cv)
            } else if (force) {
                //update
                db.update(DBHelper.TABLE_HOSTEL, cv,
                        DBHelper.TABLE_PUBLIC_ID+" = ?",
                        arrayOf("${item.getId()}") )
            }
        }
        helper.close()
    }

    private fun findCity(id: Int,db:SQLiteDatabase,tableName:String): Boolean {
        val selection = DBHelper.TABLE_PUBLIC_ID+" = ?"
        val selectionArgs = arrayOf("$id")

        val cursor:Cursor? = db.query(
                tableName,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null)

        val result = cursor?.count == 0

        cursor?.close()

        return result
    }

    fun getCities():List<City>{
        val db = helper.writableDatabase

        val cursor:Cursor? = db.query(
                DBHelper.TABLE_CITY,
                null,
                null,
                null,
                null,
                null,
                null)

        val cities:ArrayList<City> = ArrayList()

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val city = City()
                    for (cn in cursor.columnNames) {

                        when(cn){
                            DBHelper.TABLE_PUBLIC_ID->city.setId(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_LABEL->city.setLabel(cursor.getString(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_CITY_COUNT_PEOPLE->city.setId(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_CITY_COUNT_HOSTEL->city.setId(cursor.getInt(cursor.getColumnIndex(cn)))
                        }
                    }
                    cities.add(city)
                } while (cursor.moveToNext())
            }
            cursor?.close()
        }

        helper.close()

        return cities
    }

    fun getHostels(id:Int?):List<Hostel>{
        val db = helper.writableDatabase

        val selection = DBHelper.TABLE_PUBLIC_ID+" = ?"
        val selectionArgs = arrayOf("$id")

        val cursor:Cursor? = db.query(
                DBHelper.TABLE_HOSTEL,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null)

        val hostels:ArrayList<Hostel> = ArrayList()

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val hostel = Hostel()
                    for (cn in cursor.columnNames) {

                        when(cn){
                            DBHelper.TABLE_PUBLIC_ID->hostel.setId(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_LABEL-> hostel.setLabel(cursor.getString(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_HOSTEL_CITY_ID->hostel.setIdCity(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_HOSTEL_RATE->hostel.setRate(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_HOSTEL_ABOUT->hostel.setAbout(cursor.getString(cursor.getColumnIndex(cn)))
                        }
                    }
                    hostels.add(hostel)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        helper.close()

        return hostels
    }


    fun getHostels():List<Hostel>{

        val db = helper.writableDatabase

        val cursor:Cursor? = db.query(
                DBHelper.TABLE_HOSTEL,
                null,
                null,
                null,
                null,
                null,
                null)

        val hostels:ArrayList<Hostel> = ArrayList()

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val hostel = Hostel()
                    for (cn in cursor.columnNames) {

                        when(cn){
                            DBHelper.TABLE_PUBLIC_ID->hostel.setId(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_LABEL-> hostel.setLabel(cursor.getString(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_HOSTEL_CITY_ID->hostel.setIdCity(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_HOSTEL_RATE->hostel.setRate(cursor.getInt(cursor.getColumnIndex(cn)))
                            DBHelper.TABLE_HOSTEL_ABOUT->hostel.setAbout(cursor.getString(cursor.getColumnIndex(cn)))
                        }
                    }
                    hostels.add(hostel)
                } while (cursor.moveToNext())
            }
            cursor.close()
        }

        helper.close()

        return hostels
    }

}