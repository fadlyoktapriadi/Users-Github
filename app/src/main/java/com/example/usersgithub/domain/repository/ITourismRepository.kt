package com.example.usersgithub.domain.repository

import androidx.lifecycle.LiveData
import com.bumptech.glide.load.engine.Resource
import com.example.usersgithub.domain.model.UserFav

interface IUserFavRepository {

//    fun getFavorite(): LiveData<Resource<List<UserFav>>>
//
//    fun getFavoriteByLogin(login: String): LiveData<Resource<List<UserFav>>>

    fun setFavorite(userFav: UserFav)

    fun deleteFavorite(userFav: UserFav)

}