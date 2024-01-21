package com.example.karyawan
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteException

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {


    companion object {
        val DATABASE_VERSION = 2
        val DATABASE_NAME = "STA"
        val TABLE_NAME = "Karyawan"
        val KEY_ID = "ID"
        val KEY_NAME = "NAME"
        val KEY_AGE = "AGE"
        val KEY_TGLMASUK = "TGLMASUK"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_AGE + " TEXT," + KEY_TGLMASUK + " TEXT" +")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun insertData(dataKaryawan: DataKaryawan):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, dataKaryawan.karyawanID)
        contentValues.put(KEY_NAME, dataKaryawan.karyawanName)
        contentValues.put(KEY_TGLMASUK,dataKaryawan.karyawanMasuk )
        contentValues.put(KEY_AGE,dataKaryawan.karyawanAge)
        val success = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return success
    }

    fun viewAllKaryawan():List<DataKaryawan>{
        val karyawanList :ArrayList<DataKaryawan> = ArrayList<DataKaryawan>()
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val db = this.readableDatabase
        lateinit var cursor: Cursor
    try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var karyawanId: Int
        var karyawanName: String
        var karyawanAge: String
        var karyawanMasuk: String
        if (cursor.moveToFirst()) {
            do {
                karyawanId = cursor.getInt(with(cursor) { getColumnIndex("ID") })
                karyawanName = cursor.getString(with(cursor) { getColumnIndex("NAME") })
                karyawanAge = cursor.getString(with(cursor) { getColumnIndex("AGE") })
                karyawanMasuk = cursor.getString(with(cursor) { getColumnIndex("TGLMASUK") })
                val emp= DataKaryawan(karyawanID = karyawanId, karyawanName = karyawanName, karyawanAge = karyawanAge, karyawanMasuk = karyawanMasuk)
                karyawanList.add(emp)
            } while (cursor.moveToNext())
        }
        return karyawanList
    }
}