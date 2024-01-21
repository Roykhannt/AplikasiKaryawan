package com.example.aplikasikaryawan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasikaryawan.model.dataRepo

class RepoAdapter(private val repos: List<dataRepo>) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repos[position]
        holder.bind(repo)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val idTextView: TextView = itemView.findViewById(R.id.repo_id)
        private val nameTextView: TextView = itemView.findViewById(R.id.repo_name)
        private val urlTextView: TextView = itemView.findViewById(R.id.repo_url)

        fun bind(repo: dataRepo) {
            idTextView.text = "ID: ${repo.id}"
            nameTextView.text = "Name: ${repo.name}"
            urlTextView.text = "URL: ${repo.html_url}"
        }
    }
}