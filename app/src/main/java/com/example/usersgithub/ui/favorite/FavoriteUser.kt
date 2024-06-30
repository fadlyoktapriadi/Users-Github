package com.example.usersgithub.ui.favorite

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usersgithub.data.local.entity.FavoriteUserGithub
import com.example.usersgithub.databinding.ActivityFavoriteUserBinding
import com.example.usersgithub.factory.ViewModelFactory

class FavoriteUser : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding

    private val viewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavorite.layoutManager = layoutManager

        setupData()
    }

    private fun setupData(){
        viewModel.getFavorite().observe(this){
            setDataFavorite(it)
        }
    }

    private fun setDataFavorite(userfav: List<FavoriteUserGithub>){
        val adapter = FavoriteAdapter()
        val items = arrayListOf<FavoriteUserGithub>()
        userfav.map {
            val item = FavoriteUserGithub(login = it.login, avatarUrl = it.avatarUrl)
            items.add(item)
        }
        adapter.submitList(items)
        binding.rvFavorite.adapter = adapter
    }

}