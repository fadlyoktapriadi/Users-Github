package com.example.usersgithub.ui.main

import android.content.Intent
import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.databinding.ItemUserBinding
import androidx.recyclerview.widget.ListAdapter
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.usersgithub.ui.detail.DetailActivity


class UserAdapter : ListAdapter<UserGithub, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)


        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("login", user.login)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserGithub) {
            binding.tvUsername.text = user.login
            Glide.with(binding.photoProfile.context)
                .load(user.avatarUrl)
                .into(binding.photoProfile);
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserGithub>() {
            override fun areItemsTheSame(oldItem: UserGithub, newItem: UserGithub): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserGithub, newItem: UserGithub): Boolean {
                return oldItem == newItem
            }
        }
    }
}
