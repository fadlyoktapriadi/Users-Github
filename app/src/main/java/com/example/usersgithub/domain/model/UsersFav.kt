package com.example.usersgithub.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserFav(
    var login: String = "",
    var avatarUrl: String = ""
) : Parcelable