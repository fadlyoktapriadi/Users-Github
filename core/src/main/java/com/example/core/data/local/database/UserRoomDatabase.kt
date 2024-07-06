package com.example.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.data.local.entity.UserGithubEntity

@Database(entities = [UserGithubEntity::class], version = 1, exportSchema = false)

abstract class UserRoomDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}
