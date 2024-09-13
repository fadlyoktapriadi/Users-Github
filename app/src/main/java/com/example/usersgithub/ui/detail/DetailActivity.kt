package com.example.usersgithub.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.usersgithub.R
import com.example.core.data.Result
import com.example.usersgithub.databinding.ActivityDetailBinding
import com.example.core.domain.model.User
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    private val detailViewModel: DetailViewModel by viewModel()

    private lateinit var user: User

    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val username = intent.getStringExtra("username").toString()

        setupview(username)
        setupFollow()
        setupFavorite()
    }

    @SuppressLint("SetTextI18n")
    private fun setupview(username: String) {
        detailViewModel.getDetailUser(username).observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.tvUsernameDetail.text = it.data!!.login
                    binding.tvNameAlias.text = it.data!!.name
                    binding.tvFollowing.text = "${it.data!!.following} Following"
                    binding.tvFollowers.text = "${it.data!!.followers} Followers"
                    Glide.with(this)
                        .load(it.data!!.avatarUrl)
                        .into(binding.profileImageDetail)
                    user = it.data!!
                    checkFavorite(username)
                }

                is Result.Error -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, "Gagal ambil data ${it.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun checkFavorite(username: String) {
        detailViewModel.getDetail(username)?.observe(this) {
            if (it.isFavorite != null) {
                isFavorite = true
                binding.fabFavorite.setImageResource(R.drawable.ic_favorited)
            } else {
                isFavorite = false
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
            }
        }
    }

    private fun setupFavorite() {
        binding.fabFavorite.setOnClickListener {
            if (isFavorite == true) {
                user.isFavorite = false
                detailViewModel.deleteUser(user)
                Toast.makeText(this, "Berhasil menghapus dari favorite", Toast.LENGTH_SHORT).show()
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
            } else {
                user.isFavorite = true
                detailViewModel.insertUser(user)
                Toast.makeText(this, "Berhasil menambahkan ke favorite", Toast.LENGTH_SHORT).show()
                binding.fabFavorite.setImageResource(R.drawable.ic_favorited)
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

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}
