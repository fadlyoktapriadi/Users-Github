package com.example.usersgithub.di

import android.content.Context
import com.example.usersgithub.data.api.ApiConfig
import com.example.usersgithub.data.local.preference.SettingPreferences
import com.example.usersgithub.data.local.preference.dataStore
import com.example.usersgithub.repository.UsersRepository

object Injection {
    fun provideRepository(context: Context): UsersRepository {
        val apiService = ApiConfig.getApiService()
        val pref = SettingPreferences.getInstance(context.dataStore)
        return UsersRepository.getInstance(context, apiService, pref)
    }
}