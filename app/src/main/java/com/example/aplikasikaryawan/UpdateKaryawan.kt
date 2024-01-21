package com.example.aplikasikaryawan

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import com.example.aplikasikaryawan.databinding.ActivityUpdateKaryawanBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class UpdateKaryawan : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateKaryawanBinding
    private lateinit var db: DatabaseHandler
    private var karyawanId: String = ""
    private var selectedDate: Date = Date()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateKaryawanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= DatabaseHandler(this)

        karyawanId = intent.getStringExtra("karyawan_id") ?: ""

        if(karyawanId.isEmpty()){
            finish()
            return
        }

        val karyawan = db.getKaryawanById(karyawanId)

        binding.etUpdateNama.setText(karyawan.userName)
        binding.etUpdateTglmasuk.setText(karyawan.userTMK)
        binding.etUpdateTglmasuk.setOnClickListener {
            showDatePickerDialog()
        }
        binding.etUpdateUsia.setText(karyawan.userAge.toString())

        binding.btUpdateSave.setOnClickListener {

            val newName = binding.etUpdateNama.text.toString()
            val newTMK = binding.etUpdateTglmasuk.text.toString()
            val newAge = binding.etUpdateUsia.text.toString().toInt()
            val updateKaryawan = dataKaryawan(karyawanId, newName,newTMK,newAge)
            db.update(updateKaryawan)
            finish()
            Toast.makeText(this, "Data Succes Update", Toast.LENGTH_SHORT).show()
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
                binding.etUpdateTglmasuk.setText(SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(selectedDate))
            },
            currentYear,
            currentMonth,
            currentDay
        )

        datePickerDialog.show()
    }
}