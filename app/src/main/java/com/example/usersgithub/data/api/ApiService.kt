package com.example.usersgithub.data.api

import com.example.usersgithub.data.api.response.DetailUserResponse
import com.example.usersgithub.data.api.response.FollowResponse
import com.example.usersgithub.data.api.response.FollowResponseItem
import com.example.usersgithub.data.api.response.GithubResponse
import retrofit2.http.*

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") login: String
    ): GithubResponse

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String
    ): DetailUserResponse

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String
    ): List<FollowResponseItem>

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String
    ): List<FollowResponseItem>
}