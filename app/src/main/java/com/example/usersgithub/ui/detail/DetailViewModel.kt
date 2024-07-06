package com.example.usersgithub.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.User
import com.example.core.domain.usecase.UserUseCase
import kotlinx.coroutines.launch


class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getDetailUser(username: String) = userUseCase.getDetailUser(username).asLiveData()

    fun getDetail(username: String) = userUseCase.getDetail(username)?.asLiveData()

    fun getFollowers(username: String) = userUseCase.getFollowers(username).asLiveData()

    fun getFollowing(username: String) = userUseCase.getFollowing(username).asLiveData()

    fun insertUser(user: User) {
        viewModelScope.launch {
            userUseCase.insertUserFav(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userUseCase.deleteUserFav(user)
        }
    }



}