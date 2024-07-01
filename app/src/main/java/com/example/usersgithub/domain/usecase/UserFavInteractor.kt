package com.example.usersgithub.domain.usecase

import com.example.usersgithub.data.UsersRepository
import com.example.usersgithub.domain.model.UserFav
import com.example.usersgithub.domain.repository.IUserFavRepository

class UserFavInteractor (private val usersRepository: IUserFavRepository): UserFavUseCase  {

    override fun setFavorite(userfav: UserFav) = usersRepository.setFavorite(userfav)

    override fun deleteFavorite(userfav: UserFav) = usersRepository.deleteFavorite(userfav)
}