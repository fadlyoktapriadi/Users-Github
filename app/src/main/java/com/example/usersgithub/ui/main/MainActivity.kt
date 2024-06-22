package com.example.usersgithub.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersgithub.R
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.databinding.ActivityMainBinding
import com.example.usersgithub.repository.Result
import com.example.usersgithub.repository.ViewModelFactory
import com.example.usersgithub.ui.favorite.FavoriteUser
import com.example.usersgithub.ui.setting.SettingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()

    }

    private fun setupAction() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter

        viewModel.getUsers().observe(this) {
            when (it) {
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Result.Success -> {
                    adapter.submitList(it.data)
                    binding.progressBar.visibility = View.GONE
                }

                is Result.Error -> {
                    Toast.makeText(
                        this,
                        "Gagal ambil data ${it.error}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("TEST ERROR", it.error)
                }
            }
        }
    }

    private fun test(){
        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()

//                        mainViewModel.findUsersBySearch(searchView.text.toString())

                    false
                }

            searchBar.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
                Toolbar.OnMenuItemClickListener,
                androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.favorite_item -> {
                            val favoriteIntent =
                                Intent(this@MainActivity, FavoriteUser::class.java)
                            startActivity(favoriteIntent)
                            true
                        }

                        R.id.setting_item -> {
                            val favoriteIntent =
                                Intent(this@MainActivity, SettingActivity::class.java)
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
}