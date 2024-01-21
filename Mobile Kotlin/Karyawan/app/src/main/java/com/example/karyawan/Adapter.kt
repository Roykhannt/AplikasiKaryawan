package com.example.karyawan

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter(private val context: Activity, private val id: Array<String>, private val name: Array<String>,private val tglMasuk: Array<String>, private val age: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, name) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val ageText = rowView.findViewById(R.id.textViewAge) as TextView
        val MasukText = rowView.findViewById(R.id.textViewTglMasuk) as TextView

        idText.text = "Id: ${id[position]}"
        nameText.text = "Nama: ${name[position]}"
        ageText.text = "Usia: ${age[position]}"
        MasukText.text = "Tanggal Masuk: ${tglMasuk[position]}"
        return rowView
    }
}