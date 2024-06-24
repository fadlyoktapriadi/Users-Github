package com.example.usersgithub.ui.main

import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.UsersRepository

class MainViewModel(private val repository: UsersRepository) : ViewModel() {

    fun getUsers() = repository.getUsers()

}