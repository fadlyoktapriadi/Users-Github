package com.example.usersgithub.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.usersgithub.data.local.entity.UserGithubEntity

@Database(entities = [UserGithubEntity::class], version = 1, exportSchema = false)

abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}
