package com.example.usersgithub.repository

import com.example.usersgithub.data.api.ApiService
import android.annotation.SuppressLint
import android.content.Context
import com.example.usersgithub.data.local.database.FavoriteDao
import com.example.usersgithub.data.local.database.FavoriteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.usersgithub.data.api.response.GithubResponse
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.data.local.preference.SettingPreferences

class UsersRepository private constructor(
    private val context: Context,
    private val apiService: ApiService,
    private val settingPreference: SettingPreferences
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





//    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUserGithub> =
//        mFavoriteDao.getFavoriteUserByUsername(username)
//
//    fun getFavoriteUser(): LiveData<List<FavoriteUserGithub>> = mFavoriteDao.getFavoriteUser()
//
//    fun insert(favoriteuser: FavoriteUserGithub) {
//        executorService.execute { mFavoriteDao.insert(favoriteuser) }
//    }
//
//    fun delete(favoriteuser: FavoriteUserGithub) {
//        executorService.execute { mFavoriteDao.delete(favoriteuser) }
//    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: UsersRepository? = null
        fun getInstance(
            context: Context,
            apiService: ApiService,
            userPreference: SettingPreferences
        ): UsersRepository =
            instance ?: synchronized(this) {
                instance ?: UsersRepository(context, apiService, userPreference)
            }.also { instance = it }
    }
}