package com.example.usersgithub.ui.detail

import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.UsersRepository


class DetailViewModel(private val repository: UsersRepository) : ViewModel() {

    fun getDetailUser(username: String) = repository.getDetailUser(username)

    fun getFollowing(username: String) = repository.getFollowing(username)

    fun getFollowers(username: String) = repository.getFollowers(username)

}