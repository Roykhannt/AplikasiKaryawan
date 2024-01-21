package com.example.aplikasikaryawan

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "STA"
        private const val TABLE_NAME = "KaryawanSTA"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_TMK = "tmk"
        private const val COLUMN_AGE = "age"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID TEXT(30) PRIMARY KEY, $COLUMN_NAME TEXT(30), $COLUMN_TMK TEXT, $COLUMN_AGE INTEGER )"
        db?.execSQL(createTableQuery)

        insertDefaultData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertData(karyawan: dataKaryawan){
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, karyawan.userId)
            put(COLUMN_NAME, karyawan.userName)
            put(COLUMN_TMK, karyawan.userTMK)
            put(COLUMN_AGE, karyawan.userAge)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllData(): List<dataKaryawan>{
        val karyawanList = mutableListOf<dataKaryawan>()
        val db = readableDatabase
        val query= "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        while (cursor.moveToNext()){
            val id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val TMK = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TMK))
            val age =  cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))

            val karyawan = dataKaryawan(id,name,TMK,age)
            karyawanList.add(karyawan)
        }
        cursor.close()
        db.close()
        return karyawanList
    }

    fun update(karyawan: dataKaryawan){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID, karyawan.userId)
            put(COLUMN_NAME, karyawan.userName)
            put(COLUMN_TMK, karyawan.userTMK)
            put(COLUMN_AGE, karyawan.userAge)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(karyawan.userId)
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getKaryawanById(karyawanId: String): dataKaryawan{
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID ='$karyawanId'"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
        val TMK = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TMK))
        val age =  cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))

        cursor.close()
        db.close()
        return dataKaryawan(id,name,TMK,age)
    }

    fun deleteData(karyawanId: String){
        val db= writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(karyawanId)
        db.delete(TABLE_NAME, whereClause,whereArgs)
        db.close()
    }
    private fun insertDefaultData(db: SQLiteDatabase?) {
        val defaultDataList = listOf(
            dataKaryawan("001", "Andi", "02-03-2012", 25),
            dataKaryawan("002", "Anto", "02-06-2013", 24),
            dataKaryawan("003", "Adi", "21-05-2000", 27),
            dataKaryawan("004", "Amin", "05-08-2018", 31),
            dataKaryawan("005", "Roy", "01-03-2024", 23)
        )
        defaultDataList.forEach { data ->
            val values = ContentValues().apply {
                put(COLUMN_ID, data.userId)
                put(COLUMN_NAME, data.userName)
                put(COLUMN_TMK, data.userTMK)
                put(COLUMN_AGE, data.userAge)
            }
            db?.insert(TABLE_NAME, null, values)
        }
    }
}