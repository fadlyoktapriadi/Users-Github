package com.example.usersgithub.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.example.core.data.Result
import com.example.core.domain.model.User
import com.example.core.domain.usecase.UserUseCase

class MainViewModel(userUseCase: UserUseCase) : ViewModel() {
    var username: MutableLiveData<String> = MutableLiveData()

    val users: LiveData<Result<List<User>>> = username.switchMap { query ->
        userUseCase.getUsers(query).asLiveData()
    }
}