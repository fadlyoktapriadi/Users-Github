package com.example.usersgithub.domain.usecase

import com.example.usersgithub.domain.model.UserFav

interface UserFavUseCase {
    fun setFavorite(userfav: UserFav)

    fun deleteFavorite(userfav: UserFav)
}