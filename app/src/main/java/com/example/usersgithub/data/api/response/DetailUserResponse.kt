package com.example.usersgithub.data.api.response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("login")
    val login: String?,

    @field:SerializedName("avatar_url")
    val avatarUrl: String?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("followers")
    val followers: Int?,

    @field:SerializedName("following")
    val following: Int?

    )
