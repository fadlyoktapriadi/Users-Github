package com.example.usersgithub.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.local.database.FavoriteUserGithub
import com.example.usersgithub.repository.FavoriteRepository

class FavoriteViewModel(application: Application) : ViewModel() {

    private val mFavoriteRepository: FavoriteRepository = FavoriteRepository(application)

    fun getFavoriteUser(): LiveData<List<FavoriteUserGithub>> = mFavoriteRepository.getFavoriteUser()
}