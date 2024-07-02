package com.example.usersgithub.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.usersgithub.data.local.entity.UserGithubEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersDao {

    @Query("SELECT * from user_github_tbl ORDER BY login ASC")
    fun getUserFavorite(): Flow<List<UserGithubEntity>>

    @Query("SELECT * FROM user_github_tbl WHERE login = :username")
    fun getDetail(username: String): Flow<UserGithubEntity>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserGithubEntity?)

    @Delete
    suspend fun deleteUser(user: UserGithubEntity): Int
}