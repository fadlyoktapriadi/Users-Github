package com.example.usersgithub.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.usersgithub.R
import com.example.usersgithub.databinding.ActivityDetailBinding
import com.example.usersgithub.factory.ViewModelFactory
import com.example.usersgithub.factory.Result
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    private val viewModel by viewModels<DetailViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupview()
        setuptab()
    }


    @SuppressLint("SetTextI18n")
    private fun setupview() {
        viewModel.getDetailUser(intent.getStringExtra("login").toString()).observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvUsernameDetail.text = it.data.login
                    binding.tvNameAlias.text = it.data.name
                    binding.tvFollowing.text = "${it.data.following} Following"
                    binding.tvFollowers.text = "${it.data.followers} Followers"
                    Glide.with(this)
                        .load(it.data.avatarUrl)
                        .into(binding.profileImageDetail)
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Gagal ambil data ${it.error}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setuptab() {
//        val sectionsPagerAdapter = SectionsPagerAdapter(this, intent.getStringExtra("login").toString())
//        binding.viewPager.adapter = sectionsPagerAdapter
//        binding.tabs.setupWithViewPager(binding.viewPager)

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)
        val adapter = SectionsPagerAdapter(this)

        viewPager2.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    //
//
////        val pref = SettingPreferences.getInstance(application.dataStore)
////        val settingviewModel = ViewModelProvider(this, SettingModelFactory(pref)).get(
////            SettingViewModel::class.java
////        )
////
////        settingviewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
////            if (isDarkModeActive) {
////                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
////            } else {
////                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
////            }
////        }
//
//        binding = ActivityDetailUserGithubBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val detailUserViewModel = obtainViewModel(this@DetailUserGithub)
//
//        detailUserViewModel.username = intent.extras?.getString("username").toString()
//
//        detailUserViewModel.detailUser.observe(this) { userDetail ->
//            setDataDetailUser(userDetail)
//        }
//
//        detailUserViewModel.isLoading.observe(this) {
//            showLoading(it)
//        }
//
//
//
//        val username = intent.extras?.getString("username").toString()
//        val avatar = intent.extras?.getString("avatar").toString()
//
//        val bundle = Bundle()
//        bundle.putString(intent.extras?.getString("username").toString(), username)
//
////        detailUserViewModel.getFavoriteUserByUsername(username).observe(this) { userFav ->
////            favorite = userFav
////            when(favorite){
////                null -> {
////                    binding.fabFavorite.setImageDrawable(
////                        ContextCompat.getDrawable(
////                            binding.fabFavorite.context,
////                            R.drawable.ic_favorite
////                        )
////                    )
////                }
////                else -> {
////                    binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorite.context, R.drawable.ic_favorited))
////                }
////            }
////        }
////
////        binding.fabFavorite.setOnClickListener{
////            when(favorite){
////                null -> {
////                    favorite = FavoriteUserGithub()
////                    favorite.let { favorite ->
////                        favorite?.username = username
////                        favorite?.avatarUrl = avatar
////                    }
////                    binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorite.context, R.drawable.ic_favorited))
////                    detailUserViewModel.insert(favorite as FavoriteUserGithub)
////                }
////                else -> {
////                    binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(binding.fabFavorite.context, R.drawable.ic_favorite))
////                    detailUserViewModel.delete(favorite as FavoriteUserGithub)
////                }
////            }
////        }
////
////    }
//
//    private fun setDataDetailUser(detailUser: DetailUserResponse) {
//        binding.tvUsernameDetail.text = detailUser.login
//        binding.tvNameAlias.text = detailUser.name
//        binding.tvFollowing.text = "${detailUser.following} Following"
//        binding.tvFollowers.text = "${detailUser.followers} Followers"
//        Glide.with(this)
//            .load(detailUser.avatarUrl)
//            .into(binding.profileImageDetail)
//    }
//
//    private fun obtainViewModel(activity: AppCompatActivity): DetailUserViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(DetailUserViewModel::class.java)
//    }
//
//    private fun showLoading(state: Boolean) {
//        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
//    }
//
    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
