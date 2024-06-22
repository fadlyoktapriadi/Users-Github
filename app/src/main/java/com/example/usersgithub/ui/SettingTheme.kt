package com.example.usersgithub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.usersgithub.R
import com.example.usersgithub.repository.SettingModelFactory
import com.example.usersgithub.model.SettingViewModel
import com.example.usersgithub.data.preference.SettingPreferences
import com.example.usersgithub.data.preference.dataStore
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingTheme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting_theme)

        supportActionBar?.hide()

        val switchTheme = findViewById<SwitchMaterial>(R.id.nightmode)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingviewModel = ViewModelProvider(this, SettingModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingviewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switchTheme.isChecked = false
            }
        }

        switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingviewModel.saveThemeSetting(isChecked)
        }
    }
}