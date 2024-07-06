package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int?,
    val login: String?,
    val avatarUrl: String?,
    val name: String?,
    val followers: Int?,
    val following: Int?,
    var isFavorite: Boolean?
): Parcelable