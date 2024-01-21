package com.example.submission2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.adapter.UserAdapter
import com.example.submission2.databinding.ActivityMainBinding
import com.example.submission2.model.DataUser
import com.example.submission2.viewModel.UserViewModel


class MainActivity : AppCompatActivity() {

    private  lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallBack(object :UserAdapter.OnItemClickCallBack{
            override fun onItemClicked(data: DataUser) {
                Intent(this@MainActivity, DetailUser::class.java).also {
                    it.putExtra(DetailUser.EXTRA_USERNAME, data.username)
                    startActivity(it)
                }
            }
        })
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        binding.apply {
            rvUser.layoutManager= LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter=adapter

            searchBtn.setOnClickListener{

            }
            searchEt.setOnKeyListener { v, keyCode, event ->
                if(event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    CariUser()
                    return@setOnKeyListener true
                }
                    return@setOnKeyListener false
            }
        }
        viewModel.getCariUsers().observe(this,{
            if(it!=null){
                adapter.setList(it)
                showTunggu(false)
            }
        })

    }
    private fun CariUser(){
        binding.apply {
            val query =searchEt.text.toString()
            if(query.isEmpty()) return
            showTunggu(true)
            viewModel.setCariUsers(query)
        }
    }


    private fun showTunggu(state: Boolean){
        if(state){
            binding.pgMainActivity.visibility= View.VISIBLE
        }else{
            binding.pgMainActivity.visibility=View.GONE
        }
    }
}