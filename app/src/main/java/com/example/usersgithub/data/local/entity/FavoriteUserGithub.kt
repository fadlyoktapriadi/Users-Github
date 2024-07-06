package com.example.usersgithub.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_github")
data class UserGithubEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "login")
    var login: String?,

    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String?,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "followers")
    var followers: Int?,

    @ColumnInfo(name = "following")
    var following: Int?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean?
)