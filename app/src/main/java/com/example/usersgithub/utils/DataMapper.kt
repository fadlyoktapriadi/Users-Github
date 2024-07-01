package com.example.usersgithub.utils

import com.example.usersgithub.data.local.entity.FavoriteUserGithub
import com.example.usersgithub.domain.model.UserFav

object DataMapper {

    fun mapEntitiesToDomain(input: List<FavoriteUserGithub>): List<UserFav> =
        input.map {
            UserFav(
                login = it.login,
                avatarUrl = it.avatarUrl!!
            )
        }
    fun mapDomainToEntity(input: UserFav) = FavoriteUserGithub(
        login = input.login,
        avatarUrl = input.avatarUrl
    )
}