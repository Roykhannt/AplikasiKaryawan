package com.example.submission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.submission2.databinding.RowItemBinding
import com.example.submission2.model.DataUser

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val list = ArrayList<DataUser>()
    private var onItemClickCallBack :OnItemClickCallBack?= null

    fun setOnItemClickCallBack (onItemClickCallBack: OnItemClickCallBack){
        this.onItemClickCallBack= onItemClickCallBack
    }
    fun setList(users: ArrayList<DataUser>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    inner class UserViewHolder(val binding: RowItemBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bind(user: DataUser){
                binding.root.setOnClickListener{
                    onItemClickCallBack?.onItemClicked(user)
                }
                binding.apply {
                    Glide.with(itemView)
                        .load(user.avatar)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .into(imgItemAvatar)
                    tvItemName.text=user.username

                }


            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val Hasil = RowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder((Hasil))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int= list.size

    interface OnItemClickCallBack{
        fun onItemClicked(data: DataUser)
    }

}