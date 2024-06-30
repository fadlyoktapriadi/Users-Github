package com.example.usersgithub.data

import com.example.usersgithub.data.api.ApiService
import android.annotation.SuppressLint
import android.content.Context
import com.example.usersgithub.data.local.database.FavoriteDao
import com.example.usersgithub.data.local.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.usersgithub.data.api.response.DetailUserResponse
import com.example.usersgithub.data.api.response.FollowResponseItem
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.data.local.entity.FavoriteUserGithub

class UsersRepository private constructor(
    private val context: Context,
    private val apiService: ApiService
) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(context)
        mFavoriteDao = db.favoriteDao()
    }

    fun getUsers(): LiveData<Result<List<UserGithub>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers("fadly")
            emit(Result.Success(response.items))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getSearch(query: String): LiveData<Result<List<UserGithub>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers(query)
            emit(Result.Success(response.items))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getDetailUser(username: String): LiveData<Result<DetailUserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailUser(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getFollowing(username: String): LiveData<Result<List<FollowResponseItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFollowing(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getFollowers(username: String): LiveData<Result<List<FollowResponseItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFollowers(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getFavorite(): LiveData<List<FavoriteUserGithub>> = mFavoriteDao.getFavoriteUser()

    fun getFavoriteByLogin(login: String): LiveData<FavoriteUserGithub> =
        mFavoriteDao.getFavoriteUserByLogin(login)

    fun insertFavorite(favoriteuser: FavoriteUserGithub) {
        executorService.execute { mFavoriteDao.insert(favoriteuser) }
    }

    fun deleteFavorite(favoriteuser: FavoriteUserGithub) {
        executorService.execute { mFavoriteDao.delete(favoriteuser) }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: UsersRepository? = null
        fun getInstance(
            context: Context,
            apiService: ApiService
        ): UsersRepository =
            instance ?: synchronized(this) {
                instance ?: UsersRepository(context, apiService)
            }.also { instance = it }
    }
}