package com.example.usersgithub.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.usersgithub.R
import com.example.usersgithub.adapter.SectionsPagerAdapter
import com.example.usersgithub.data.api.response.DetailUserResponse
import com.example.usersgithub.data.database.FavoriteUserGithub
import com.example.usersgithub.databinding.ActivityDetailUserGithubBinding
import com.example.usersgithub.repository.SettingModelFactory
import com.example.usersgithub.repository.ViewModelFactory
import com.example.usersgithub.model.DetailUserViewModel
import com.example.usersgithub.model.SettingViewModel
import com.example.usersgithub.data.preference.SettingPreferences
import com.example.usersgithub.data.preference.dataStore
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailUserGithub : AppCompatActivity() {

    lateinit var binding: ActivityDetailUserGithubBinding

    private var favorite: FavoriteUserGithub? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_user_github)

        supportActionBar?.hide()

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

        binding = ActivityDetailUserGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailUserViewModel = obtainViewModel(this@DetailUserGithub)

        detailUserViewModel.username = intent.extras?.getString("username").toString()

        detailUserViewModel.detailUser.observe(this) { userDetail ->
            setDataDetailUser(userDetail)
        }

        detailUserViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)
        val adapter = SectionsPagerAdapter(this)

        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val username = intent.extras?.getString("username").toString()
        val avatar = intent.extras?.getString("avatar").toString()

        val bundle = Bundle()
        bundle.putString(intent.extras?.getString("username").toString(), username)

        detailUserViewModel.getFavoriteUserByUsername(username).observe(this) { userFav ->
            favorite = userFav
            when(favorite){
                null -> {
                    binding.fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.fabFavorite.context,
                            R.drawable.ic_favorite
                        )
                    )
                }
                else -> {
                    binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorite.context, R.drawable.ic_favorited))
                }
            }
        }

        binding.fabFavorite.setOnClickListener{
            when(favorite){
                null -> {
                    favorite = FavoriteUserGithub()
                    favorite.let { favorite ->
                        favorite?.username = username
                        favorite?.avatarUrl = avatar
                    }
                    binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorite.context, R.drawable.ic_favorited))
                    detailUserViewModel.insert(favorite as FavoriteUserGithub)
                }
                else -> {
                    binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorite.context, R.drawable.ic_favorite))
                    detailUserViewModel.delete(favorite as FavoriteUserGithub)
                }
            }
        }

    }

    private fun setDataDetailUser(detailUser: DetailUserResponse) {
        binding.tvUsernameDetail.text = detailUser.login
        binding.tvNameAlias.text = detailUser.name
        binding.tvFollowing.text = "${detailUser.following} Following"
        binding.tvFollowers.text = "${detailUser.followers} Followers"
        Glide.with(this)
            .load(detailUser.avatarUrl)
            .into(binding.profileImageDetail)
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailUserViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(DetailUserViewModel::class.java)
    }

    private fun showLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}