package com.example.usersgithub.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.api.response.GithubResponse
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.data.api.ApiConfig
import com.example.usersgithub.repository.UsersRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: UsersRepository) : ViewModel() {

    fun getUsers() = repository.getUsers()

}