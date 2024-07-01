package com.example.usersgithub.di

import android.content.Context
import com.example.usersgithub.data.UsersRepository
import com.example.usersgithub.data.api.ApiConfig
import com.example.usersgithub.data.local.database.FavoriteRoomDatabase
import com.example.usersgithub.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): UsersRepository {
        val apiService = ApiConfig.getApiService()
        val database = FavoriteRoomDatabase.getDatabase(context)
        val dao = database.favoriteDao()
        val appExecutors = AppExecutors()
        return UsersRepository.getInstance(apiService, dao, appExecutors)
    }
}