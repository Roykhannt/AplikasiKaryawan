package com.example.karyawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import com.example.karyawan.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewAllData()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }

    }


    private fun viewAllData(){

        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val karyawan: List<DataKaryawan> = databaseHandler.viewAllKaryawan()
        val karyawanArrayID = Array<String>(karyawan.size){"0"}
        val karyawanArrayName = Array<String>(karyawan.size){"null"}
        val karyawanArrayAge = Array<String>(karyawan.size){"null"}
        val karyawanArrayMasuk = Array<String>(karyawan.size){"null"}
        var index = 0
        for(k in karyawan){
            karyawanArrayID[index] = k.karyawanID.toString()
            karyawanArrayName[index] = k.karyawanName
            karyawanArrayMasuk[index] = k.karyawanMasuk
            karyawanArrayAge[index] = k.karyawanAge
            index++
        }
        //creating custom ArrayAdapter
        val listView = findViewById(R.id.listView) as ListView
        val myListAdapter = Adapter(this,karyawanArrayID,karyawanArrayName,karyawanArrayMasuk,karyawanArrayAge)
        listView.adapter = myListAdapter

    }
}