package com.example.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersgithub.databinding.ActivityFavoriteUserBinding
import com.example.usersgithub.ui.main.UserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(FavModule)

        supportActionBar?.title = "Favorite User"

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = layoutManager

        setupDataFavorite()
    }

    private fun setupDataFavorite(){
        favoriteViewModel.getFavorite().observe(this) {
            it?.let {
                val adapter = UserAdapter()
                adapter.submitList(it)
                binding.rvFavorite.adapter = adapter
            }
        }
    }
}