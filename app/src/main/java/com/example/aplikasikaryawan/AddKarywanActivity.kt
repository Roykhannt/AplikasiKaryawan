package com.example.aplikasikaryawan

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.example.aplikasikaryawan.databinding.ActivityAddKarywanBinding
import com.example.aplikasikaryawan.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddKarywanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddKarywanBinding
    private lateinit var db: DatabaseHandler
    private var selectedDate: Date = Date()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddKarywanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DatabaseHandler(this)

        binding.etTglmasuk.setOnClickListener {
            showDatePickerDialog()
        }
        binding.btSave.setOnClickListener {
            val userId = binding.etId.text.toString()
            val userName = binding.etNama.text.toString()
            val userTMK = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(selectedDate)
            val userAge = binding.etUsia.text.toString().toInt()
            val karyawan = dataKaryawan(userId,userName,userTMK,userAge)

            db.insertData(karyawan)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
        }
    }
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, day)
                selectedDate = selectedCalendar.time
                binding.etTglmasuk.setText(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(selectedDate))
            },
            currentYear,
            currentMonth,
            currentDay
        )

        datePickerDialog.show()
    }
}