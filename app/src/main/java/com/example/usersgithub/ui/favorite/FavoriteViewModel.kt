package com.example.usersgithub.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.local.database.FavoriteUserGithub
import com.example.usersgithub.repository.UsersRepository

class FavoriteViewModel(private val repository: UsersRepository) : ViewModel() {

//    private val mFavoriteRepository: UsersRepository = UsersRepository(application)

//    fun getFavoriteUser(): LiveData<List<FavoriteUserGithub>> = mFavoriteRepository.getFavoriteUser()
}