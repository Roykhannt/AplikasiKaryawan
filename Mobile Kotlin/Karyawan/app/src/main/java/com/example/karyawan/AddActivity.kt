package com.example.karyawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.karyawan.databinding.ActivityAddBinding
import com.example.karyawan.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSave.setOnClickListener {

            val id = binding.etId.text.toString()
            val name = binding.etNama.text.toString()
            val age =binding.etUsia.toString()
            val tglMasuk = binding.etTglmasuk.toString()
            val databaseHandler: DatabaseHandler= DatabaseHandler(this)

            if(id.trim()!="" && name.trim()!="" && age.trim()!="" && tglMasuk.trim()!=""){
                val status = databaseHandler.insertData(DataKaryawan(Integer.parseInt(id),name,tglMasuk,age))
                if(status > -1){
                    Toast.makeText(applicationContext,"record save", Toast.LENGTH_LONG).show()
                    binding.etId.text.clear()
                    binding.etNama.text.clear()
                    binding.etUsia.text.clear()
                    binding.etTglmasuk.text.clear()
                }
            }else{
                Toast.makeText(applicationContext,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
            }
        }

    }
}