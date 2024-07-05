package com.example.usersgithub.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.usersgithub.domain.model.User
import com.example.usersgithub.domain.usecase.UserUseCase
import kotlinx.coroutines.launch


class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getDetailUser(username: String) = userUseCase.getDetailUser(username).asLiveData()

    fun getDetail(username: String) = userUseCase.getDetail(username)?.asLiveData()

    fun getFollowers(username: String) = userUseCase.getFollowers(username).asLiveData()

    fun getFollowing(username: String) = userUseCase.getFollowing(username).asLiveData()

    fun insertUser(user: User) {
        viewModelScope.launch {
            userUseCase.insertUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userUseCase.deleteUser(user)
        }
    }



}