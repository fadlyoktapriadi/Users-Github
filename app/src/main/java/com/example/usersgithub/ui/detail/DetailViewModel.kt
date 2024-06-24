package com.example.usersgithub.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.api.response.DetailUserResponse
import com.example.usersgithub.data.api.response.FollowingFollowersResponseItem
import com.example.usersgithub.data.api.ApiConfig
import com.example.usersgithub.data.UsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel(private val repository: UsersRepository) : ViewModel() {

    fun getDetailUser(username: String) = repository.getDetailUser(username)

}