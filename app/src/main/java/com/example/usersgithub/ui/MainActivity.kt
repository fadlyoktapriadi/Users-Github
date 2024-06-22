package com.example.usersgithub.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersgithub.R
import com.example.usersgithub.adapter.UserAdapter
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.databinding.ActivityMainBinding
import com.example.usersgithub.repository.SettingModelFactory
import com.example.usersgithub.model.MainViewModel
import com.example.usersgithub.model.SettingViewModel
import com.example.usersgithub.data.preference.SettingPreferences
import com.example.usersgithub.data.preference.dataStore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingviewModel = ViewModelProvider(this, SettingModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingviewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }


        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager

        val mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainViewModel::class.java
        )

        mainViewModel.users.observe(this) { users ->
            setUserData(users.items)
        }

        mainViewModel.listUser.observe(this) { usersData ->
            setUserData(usersData)
        }

        mainViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()

                    mainViewModel.findUsersBySearch(searchView.text.toString())

                    false
                }

            searchBar.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
                Toolbar.OnMenuItemClickListener,
                androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.favorite_item -> {
                            val favoriteIntent = Intent(this@MainActivity, FavoriteUser::class.java)
                            startActivity(favoriteIntent)
                            true
                        }
                        R.id.setting_item -> {
                            val favoriteIntent = Intent(this@MainActivity, SettingTheme::class.java)
                            startActivity(favoriteIntent)
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            })
        }
    }

    private fun setUserData(Users: List<UserGithub?>?) {
        val adapter = UserAdapter()
        adapter.submitList(Users)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }



}