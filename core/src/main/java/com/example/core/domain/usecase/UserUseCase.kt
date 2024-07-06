package com.example.core.domain.usecase

import com.example.core.data.Result
import com.example.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun getUsers(query: String?): Flow<Result<List<User>>>

    fun getFollowers(username: String): Flow<Result<List<User>>>

    fun getFollowing(username: String): Flow<Result<List<User>>>

    fun getDetailUser(username: String): Flow<Result<User>>

    fun getFavoriteUser(): Flow<List<User>>

    fun getDetail(username: String): Flow<User>?

    suspend fun insertUserFav(user: User)

    suspend fun deleteUserFav(user: User): Int
}