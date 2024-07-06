package com.example.usersgithub.data.local

import com.example.usersgithub.data.local.database.UsersDao
import com.example.usersgithub.data.local.entity.UserGithubEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val usersDao: UsersDao) {

    fun getUserFavorite(): Flow<List<UserGithubEntity>> = usersDao.getUserFavorite()

    fun getDetail(username: String): Flow<UserGithubEntity>? = usersDao.getDetail(username)

    suspend fun insertUserFav(user: UserGithubEntity) = usersDao.insertUserFav(user)

    suspend fun deleteUserFav(user: UserGithubEntity) = usersDao.deleteUserFav(user)
}