package com.example.usersgithub.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.UsersRepository

class FavoriteViewModel(private val repository: UsersRepository) : ViewModel() {

    fun getFavorite() = repository.getFavorite()

}