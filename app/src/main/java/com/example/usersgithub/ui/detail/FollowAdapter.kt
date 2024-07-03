package com.example.usersgithub.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usersgithub.R
import com.example.usersgithub.data.api.response.FollowResponseItem
import com.example.usersgithub.databinding.ItemFollowBinding
import com.example.usersgithub.domain.model.User

class FollowAdapter :
    ListAdapter<User, FollowAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val follow = getItem(position)
        holder.bind(follow)
        Glide.with(holder.photoProfileFollow.getContext())
            .load(follow.avatarUrl)
            .into(holder.binding.photoProfileFollow)
    }

    class MyViewHolder(val binding: ItemFollowBinding) : RecyclerView.ViewHolder(binding.root) {
        val photoProfileFollow: ImageView = itemView.findViewById(R.id.photoProfileFollow)
        fun bind(follow: User) {
            binding.tvUsernameFollow.text = follow.login
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}