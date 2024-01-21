package com.example.aplikasikaryawan.model

import retrofit2.Call
import retrofit2.http.GET

interface GitHubApi {
    @GET("users/google/repos")
    fun getRepos(): Call<List<dataRepo>>
}