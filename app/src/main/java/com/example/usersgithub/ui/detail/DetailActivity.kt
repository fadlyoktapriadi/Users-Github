package com.example.usersgithub.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.usersgithub.R
import com.example.usersgithub.data.local.entity.FavoriteUserGithub
import com.example.usersgithub.databinding.ActivityDetailBinding
import com.example.usersgithub.factory.ViewModelFactory
import com.example.usersgithub.data.Result
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

        val username = intent.getStringExtra("login").toString()
        val avatar = intent.getStringExtra("avatar").toString()

        setupview(username)
        setupFollow()
        setupFavorite(username, avatar)
    }


    @SuppressLint("SetTextI18n")
    private fun setupview(username: String) {
        viewModel.getDetailUser(username).observe(this) {
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

    private fun setupFollow() {
        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tabs)
        val viewPager2 = findViewById<ViewPager2>(R.id.view_pager)

        viewPager2.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun setupFavorite(login: String, avatar: String) {

        val favx = FavoriteUserGithub()
        viewModel.getFavoriteByLogin(login).observe(this) { favorite ->
            
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
                    binding.fabFavorite.setImageDrawable(
                        ContextCompat.getDrawable(
                            binding.fabFavorite.context,
                            R.drawable.ic_favorited
                        )
                    )
                }
            }

            binding.fabFavorite.setOnClickListener {
                when (favorite) {
                    null -> {
                        favx.let { fav ->
                            fav?.login = login
                            fav?.avatarUrl = avatar
                        }
                        binding.fabFavorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.fabFavorite.context,
                                R.drawable.ic_favorited
                            )
                        )
                        viewModel.insertFavorite(favx)
                        Log.e("TES FAVORITE NULL", favorite.toString())
                    }

                    else -> {
                        binding.fabFavorite.setImageDrawable(
                            ContextCompat.getDrawable(
                                binding.fabFavorite.context,
                                R.drawable.ic_favorite
                            )
                        )
                        Log.e("TES FAVORITE", it.toString())
                        viewModel.deleteFavorite(favorite)
                    }
                }
            }
        }

    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
