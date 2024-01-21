package com.example.aplikasikaryawan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class KaryawanAdapter(private var karyawan: List<dataKaryawan>, context: Context): RecyclerView.Adapter<KaryawanAdapter.KaryawanViewHolder>() {


    private val db: DatabaseHandler = DatabaseHandler(context)
    class KaryawanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvID: TextView = itemView.findViewById(R.id.tvId)
        val tvName: TextView = itemView.findViewById(R.id.tvNama)
        val tvMasuk: TextView = itemView.findViewById(R.id.tvMasuk)
        val tvUsia: TextView = itemView.findViewById(R.id.tvUsia)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KaryawanViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list, parent,false)
        return KaryawanViewHolder(view)
    }

    override fun getItemCount(): Int = karyawan.size

    override fun onBindViewHolder(holder: KaryawanViewHolder, position: Int) {
        val karyawan = karyawan[position]
        holder.tvID.text = karyawan.userId
        holder.tvName.text= karyawan.userName
        holder.tvMasuk.text = karyawan.userTMK
        holder.tvUsia.text = karyawan.userAge.toString()


        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateKaryawan::class.java).apply {
                putExtra("karyawan_id", karyawan.userId)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            db.deleteData(karyawan.userId)
            refreshData(db.getAllData())
            Toast.makeText(holder.itemView.context,"Data Berhasil Di Hapus", Toast.LENGTH_SHORT).show()
        }
    }

    fun  refreshData(newKaryawan: List<dataKaryawan>){
        karyawan= newKaryawan
        notifyDataSetChanged()
    }
}