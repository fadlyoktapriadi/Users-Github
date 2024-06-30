package com.example.usersgithub.di

import android.content.Context
import com.example.usersgithub.data.api.ApiConfig
import com.example.usersgithub.data.UsersRepository

object Injection {
    fun provideRepository(context: Context): UsersRepository {
        val apiService = ApiConfig.getApiService()
        return UsersRepository.getInstance(context, apiService)
    }
}