package com.example.submission2.model

import com.google.gson.annotations.SerializedName

data class DataDetail(
    @SerializedName("id")
    val id : Int,

    @SerializedName("login")
    val username : String,

    @SerializedName("avatar_url")
    val avatar: String,

    @SerializedName("name")
    val fullname: String,

    @SerializedName("followers_url")
    val followers_url: String,

    @SerializedName("following_url")
    val following_url: String,

    @SerializedName("company")
    val company: String,

    @SerializedName("location")
    val location: String,

    @SerializedName("public_repos")
    val repository: String,

    @SerializedName("followers")
    val followers: String,

    @SerializedName("following")
    val following: String


)
