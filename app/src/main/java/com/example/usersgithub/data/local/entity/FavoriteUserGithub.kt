package com.example.usersgithub.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class FavoriteUserGithub(
    @PrimaryKey(autoGenerate = false)

    @ColumnInfo(name = "login")
    var login: String = "",

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,
) : Parcelable