package com.example.usersgithub.ui.detail

import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.UsersRepository
import com.example.usersgithub.data.local.entity.FavoriteUserGithub


class DetailViewModel(private val repository: UsersRepository) : ViewModel() {

    fun getDetailUser(username: String) = repository.getDetailUser(username)

    fun getFollowing(username: String) = repository.getFollowing(username)

    fun getFollowers(username: String) = repository.getFollowers(username)

    fun getFavorite() = repository.getFavorite()

    fun getFavoriteByLogin(login: String) = repository.getFavoriteByLogin(login)

    fun insertFavorite(favoriteuser: FavoriteUserGithub) = repository.insertFavorite(favoriteuser)

    fun deleteFavorite(favoriteuser: FavoriteUserGithub) = repository.deleteFavorite(favoriteuser)

}