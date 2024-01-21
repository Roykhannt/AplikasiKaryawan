package com.example.submission2.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission2.api.ApiClient
import com.example.submission2.model.DataDetail
import com.example.submission2.model.DataUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class FollowingViewModel: ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<DataUser>>()

    fun setListFollowing(username: String){
        ApiClient.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<DataUser>>{
                override fun onResponse(
                    call: Call<ArrayList<DataUser>>,
                    response: Response<ArrayList<DataUser>>
                ) {
                    if(response.isSuccessful){
                        listFollowing.postValue((response.body()))
                    }
                }

                override fun onFailure(call: Call<ArrayList<DataUser>>, t: Throwable) {

                }

            })
    }

    fun getFollowing(): LiveData<ArrayList<DataUser>>{
        return listFollowing
    }
}