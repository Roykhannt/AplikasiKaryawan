package com.example.submission2.model

import com.google.gson.annotations.SerializedName

class DataUser (

    @SerializedName("id")
    val id : Int,

    @SerializedName("login")
    val username : String,

    @SerializedName("avatar_url")
    val avatar: String

)