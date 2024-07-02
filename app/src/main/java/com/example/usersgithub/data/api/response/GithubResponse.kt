package com.example.usersgithub.data.api.response

import com.google.gson.annotations.SerializedName

data class GithubResponse(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("items")
	val items: List<UserGithub>
)

data class UserGithub(

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
