package com.example.submission2.viewModel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.api.ApiClient
import com.example.submission2.model.DataDetail
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

    val user = MutableLiveData<DataDetail>()


    fun setUserDetail(username: String){
        ApiClient.apiInstance
            .getUserDetail(username)
            .enqueue(object : Callback<DataDetail>{
                override fun onResponse(call: Call<DataDetail>, response: Response<DataDetail>) {
                    if(response.isSuccessful){
                        Log.d(TAG, response.toString())
                        user.postValue(response.body())

                    }
                    Log.d(TAG, response.toString())


                }

                override fun onFailure(call: Call<DataDetail>, t: Throwable) {
                    Log.d("Failure", t.message.toString())

                }

            })

    }
    fun getUserDetail(): LiveData<DataDetail>{
        return user
    }
}