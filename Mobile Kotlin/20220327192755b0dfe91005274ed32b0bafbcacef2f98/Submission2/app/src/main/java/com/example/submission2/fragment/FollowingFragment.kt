package com.example.submission2.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.R
import com.example.submission2.activity.DetailUser
import com.example.submission2.adapter.UserAdapter
import com.example.submission2.databinding.FragmentFollowingBinding
import com.example.submission2.viewModel.FollowingViewModel


class FollowingFragment : Fragment(R.layout.fragment_followers) {

    private var  _binding : FragmentFollowingBinding? =null
    private val binding get() = _binding!!
    private lateinit var viewModel : FollowingViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username:String


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments
        username = args?.getString(DetailUser.EXTRA_USERNAME).toString()

        _binding = FragmentFollowingBinding.bind(view)



        adapter= UserAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvUser.setHasFixedSize(true)
            rvUser.layoutManager= LinearLayoutManager(activity)
            rvUser.adapter= adapter
        }
        showTunggu(true)
        viewModel= ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            FollowingViewModel::class.java)
        viewModel.setListFollowing(username)
        viewModel.getFollowing().observe(viewLifecycleOwner,{
            if(it!=null){
                adapter.setList(it)
                showTunggu(false)
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showTunggu(state: Boolean){
        if(state){
            binding.pgMainActivity.visibility= View.VISIBLE
        }else{
            binding.pgMainActivity.visibility=View.GONE
        }
    }
}