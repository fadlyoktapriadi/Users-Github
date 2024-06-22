package com.example.usersgithub.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.usersgithub.data.local.preference.SettingPreferences
import com.example.usersgithub.repository.UsersRepository
import kotlinx.coroutines.launch

class SettingViewModel (private val repository: UsersRepository) : ViewModel() {

//    fun getThemeSettings(): LiveData<Boolean> {
//        return pref.getThemeSetting().asLiveData()
//    }
//
//    fun saveThemeSetting(isDarkModeActive: Boolean) {
//        viewModelScope.launch {
//            pref.saveThemeSetting(isDarkModeActive)
//        }
//    }
}