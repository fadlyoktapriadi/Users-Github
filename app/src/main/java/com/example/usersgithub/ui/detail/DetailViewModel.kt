package com.example.usersgithub.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.usersgithub.data.Result
import com.example.usersgithub.domain.model.User
import com.example.usersgithub.domain.usecase.UserUseCase




class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getDetailUser(username: String) = userUseCase.getDetailUser(username).asLiveData()

    fun getFollowers(username: String) = userUseCase.getFollowers(username).asLiveData()

    fun getFollowing(username: String) = userUseCase.getFollowing(username).asLiveData()
}