package com.example.aplikasikaryawansta

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_NAME (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AGE INTEGER,PHONE TEXT, EMAIL TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }



    companion object {
        val DATABASE_NAME = "STAtest.db"
        val TABLE_NAME = "karyawan"
        val ID = "ID"
        val NAME = "NAME"
        val AGE = "AGE"
        val TANGGAL_MASUK = "TGLMASUK" }
}