package com.example.usersgithub.domain.repository

import com.example.usersgithub.data.Result
import com.example.usersgithub.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserFavRepository {

    fun getUsers(query: String?): Flow<Result<List<User>>>

    fun getFollowers(username: String): Flow<Result<List<User>>>

    fun getFollowing(username: String): Flow<Result<List<User>>>

    fun getDetailUser(username: String): Flow<Result<User>>

    fun getFavoriteUser(): Flow<List<User>>

    fun getDetail(username: String): Flow<User>?

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User): Int

}