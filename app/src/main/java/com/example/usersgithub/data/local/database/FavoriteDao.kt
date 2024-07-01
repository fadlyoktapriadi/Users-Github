package com.example.usersgithub.data.local.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.usersgithub.data.local.entity.FavoriteUserGithub
import com.example.usersgithub.domain.model.UserFav

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoriteusergithub: FavoriteUserGithub)

    @Delete
    fun delete(favoriteusergithub: FavoriteUserGithub)

    @Query("SELECT * FROM FavoriteUserGithub")
    fun getFavoriteUser(): LiveData<List<FavoriteUserGithub>>

    @Query("SELECT * FROM FavoriteUserGithub WHERE login = :login")
    fun getFavoriteUserByLogin(login: String): LiveData<UserFav>
}