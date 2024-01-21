package com.example.aplikasikaryawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasikaryawan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DatabaseHandler
    private lateinit var adapter: KaryawanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHandler(this)
        adapter = KaryawanAdapter(db.getAllData(),this)

        binding.listView.layoutManager = LinearLayoutManager(this)
        binding.listView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, AddKarywanActivity::class.java)
            startActivity(intent)
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.refreshData(db.getAllData())
    }
}