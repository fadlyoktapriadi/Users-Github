package com.example.usersgithub.data.api.response

import com.google.gson.annotations.SerializedName

data class GithubResponse(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("items")
	val items: List<UserGithub>
)

data class UserGithub(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

)
