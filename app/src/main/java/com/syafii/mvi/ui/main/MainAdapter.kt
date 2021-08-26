package com.syafii.mvi.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.syafii.mvi.R
import com.syafii.mvi.data.model.User
import com.syafii.mvi.databinding.ItemLayoutBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var lisUser = mutableListOf<User>()

    fun setData(list : List<User>){
        lisUser.clear()
        lisUser.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindData(lisUser[position])
    }

    override fun getItemCount() = lisUser.size

    inner class MainViewHolder(private val binding : ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(user: User){
            binding.run {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(binding.root).load(user.avatar).error(R.drawable.ic_profile).into(imageViewAvatar)
            }
        }
    }
}