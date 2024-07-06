package com.example.core.data.api

import com.example.core.data.api.response.GithubResponse
import com.example.core.data.api.response.UserGithub
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") login: String
    ): GithubResponse

    @GET("users/{username}")
    suspend fun getDetailUser(@Path("username") username: String
    ): UserGithub

    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String
    ): List<UserGithub>

    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String
    ): List<UserGithub>
}