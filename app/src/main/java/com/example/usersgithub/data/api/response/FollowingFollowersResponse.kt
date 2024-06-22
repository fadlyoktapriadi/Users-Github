package com.example.usersgithub.data.api.response

import com.google.gson.annotations.SerializedName

data class FollowingFollowersResponse(

	@field:SerializedName("FollowingFollowersResponse")
	val followingFollowersResponse: List<FollowingFollowersResponseItem>
)

data class FollowingFollowersResponseItem(

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: String,
)
