package com.example.submission2.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.submission2.adapter.SectionAdapter
import com.example.submission2.databinding.ActivityDetailUserBinding
import com.example.submission2.viewModel.DetailViewModel

class DetailUser : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME= "extra_username"
    }
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)?: ""
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel=ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)
        viewModel.setUserDetail(username)

        viewModel.getUserDetail().observe(this,{
            if(it != null){
                binding.apply {
                    tvDetailName.text=it.fullname
                    tvDetailUsername.text=it.username
                    tvDetailCompany.text=it.company
                    tvDetailLocation.text=it.location
                    tvDetailNumberOfRepos.text=it.repository
                    tvDetailNumberOfFollowing.text= it.following
                    tvDetailNumberOfFollowers.text=it.followers
                    Glide.with(this@DetailUser)
                        .load(it.avatar)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(ivDetailAvatar)
                }
            }
        })

        val sectionAdapter = SectionAdapter(this,supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter= sectionAdapter
            tabs.setupWithViewPager(viewPager)
        }
    }
}
