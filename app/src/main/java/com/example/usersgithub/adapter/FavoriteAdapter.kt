package com.example.usersgithub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usersgithub.R
import com.example.usersgithub.data.database.FavoriteUserGithub
import com.example.usersgithub.databinding.ItemUserBinding
import com.example.usersgithub.ui.DetailUserGithub

class FavoriteAdapter : ListAdapter<FavoriteUserGithub, FavoriteAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val userfav = getItem(position)
        holder.bind(userfav)
        Glide.with(holder.photoProfile.getContext())
            .load(userfav.avatarUrl)
            .into(holder.photoProfile);

        holder.itemView.setOnClickListener {
            val intentDetailUserGithub =
                Intent(holder.itemView.context, DetailUserGithub::class.java)
            intentDetailUserGithub.putExtra("username", userfav.username)
            intentDetailUserGithub.putExtra("avatar", userfav.username)
            holder.itemView.context.startActivity(intentDetailUserGithub)
        }

    }

    class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        val photoProfile: ImageView = itemView.findViewById(R.id.photoProfile)
        fun bind(userfav: FavoriteUserGithub) {
            binding.tvUsername.text = userfav.username
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteUserGithub>() {
            override fun areItemsTheSame(oldItem: FavoriteUserGithub, newItem: FavoriteUserGithub): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: FavoriteUserGithub, newItem: FavoriteUserGithub): Boolean {
                return oldItem == newItem
            }
        }
    }
}