package com.example.usersgithub.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersgithub.R
import com.example.core.data.Result
import com.example.usersgithub.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setupAction()
        searchSetup()
    }

    private fun setupAction() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter

        mainViewModel.username.value = "Fadly"
        mainViewModel.users.observe(this) {
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
                        "Gagal ambil data ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun searchSetup() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()

                    searchData(searchBar.text.toString())
                    false
                }

            searchBar.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener,
                Toolbar.OnMenuItemClickListener,
                androidx.appcompat.widget.Toolbar.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem): Boolean {
                    return when (item.itemId) {
                        R.id.favorite_item -> {
                            toFavorite()
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

    private fun toFavorite() {
        try {
            startActivity(Intent(this, Class.forName("com.example.favorite.FavoriteActivity")))
        } catch (e: Exception){
            Toast.makeText(this, "Module not found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchData(query: String) {
        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val adapter = UserAdapter()
        binding.rvUser.adapter = adapter

        mainViewModel.username.value = query
        mainViewModel.users.observe(this@MainActivity) {
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
                        "Gagal ambil data ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}