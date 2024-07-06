package com.example.usersgithub.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.usersgithub.domain.usecase.UserUseCase

class FavoriteViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getFavorite() = userUseCase.getFavoriteUser().asLiveData()

}