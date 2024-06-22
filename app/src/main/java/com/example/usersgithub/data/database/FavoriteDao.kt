package com.example.usersgithub.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteusergithub: FavoriteUserGithub)

    @Delete
    fun delete(favoriteusergithub: FavoriteUserGithub)

    @Query("SELECT * FROM FavoriteUserGithub")
    fun getFavoriteUser(): LiveData<List<FavoriteUserGithub>>

    @Query("SELECT * FROM FavoriteUserGithub WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUserGithub>
}