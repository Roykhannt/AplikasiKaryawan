package com.example.submission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView


class UserDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        val data = intent.getParcelableExtra<User>("DATA")
        Log.d("Detail Data", data?.name.toString())

        val avatarR : ImageView = findViewById(R.id.img_det_avatar)
        val nameR : TextView = findViewById(R.id.tv_det_name)
        val usernameR : TextView = findViewById(R.id.tv_det_username)
        val locationR : TextView = findViewById(R.id.tv_item_location)
        val companyR : TextView = findViewById(R.id.tv_item_company)
        val repositoryR : TextView = findViewById(R.id.tv_item_repository)
        val follwrR : TextView = findViewById(R.id.tv_item_followers)
        val follwingR : TextView = findViewById(R.id.tv_item_following)


        data?.photo?.let { avatarR.setImageResource(it) }
        nameR.text=data?.name
        usernameR.text=data?.username
        locationR.text=data?.location
        companyR.text=data?.company
        repositoryR.text=data?.repository
        follwrR.text=data?.followers
        follwingR.text=data?.following



    }
}