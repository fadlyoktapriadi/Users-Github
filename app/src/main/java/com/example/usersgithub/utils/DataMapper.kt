package com.example.usersgithub.utils

import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.data.local.entity.UserGithubEntity
import com.example.usersgithub.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


object DataMapper {
    fun mapResponsesToDomain(input: List<UserGithub>): Flow<List<User>> {
        val dataArray = ArrayList<User>()
        input.map {
            val user = User(
                it.id,
                it.login,
                it.avatarUrl,
                it.name,
                it.followers,
                it.following,
                false
            )
            dataArray.add(user)
        }
        return flowOf(dataArray)
    }

    fun mapResponseToDomain(input: UserGithub): Flow<User> {
        return flowOf(
            User(
                input.id,
                input.login,
                input.avatarUrl,
                input.name,
                input.followers,
                input.following,
                false
            )
        )
    }

    fun mapEntitiesToDomain(input: List<UserGithubEntity>): List<User> =
        input.map { userEntity ->
            User(
                userEntity.id,
                userEntity.login,
                userEntity.avatarUrl,
                userEntity.name,
                userEntity.followers,
                userEntity.following,
                userEntity.isFavorite
            )
        }

    fun mapEntityToDomain(input: UserGithubEntity?): User {
        return User(
            input?.id,
            input?.login,
            input?.avatarUrl,
            input?.name,
            input?.followers,
            input?.following,
            input?.isFavorite
        )
    }

    fun mapDomainToEntity(input: User) = UserGithubEntity(
        input.id,
        input.login,
        input.avatarUrl,
        input.name,
        input.followers,
        input.following,
        input.isFavorite
    )
}