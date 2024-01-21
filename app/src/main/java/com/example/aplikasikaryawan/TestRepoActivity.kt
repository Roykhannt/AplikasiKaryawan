package com.example.aplikasikaryawan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasikaryawan.model.GitHubApi
import com.example.aplikasikaryawan.model.dataRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestRepoActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_repo)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RepoAdapter(emptyList())
        recyclerView.adapter = adapter

        fetchData()
    }

    private fun fetchData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(GitHubApi::class.java)
        val call = api.getRepos()

        call.enqueue(object : Callback<List<dataRepo>> {
            override fun onResponse(call: Call<List<dataRepo>>, response: Response<List<dataRepo>>) {
                if (response.isSuccessful) {
                    val repos = response.body()
                    if (repos != null) {
                        adapter = RepoAdapter(repos)
                        recyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<dataRepo>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}