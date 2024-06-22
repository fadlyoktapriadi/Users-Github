package com.example.usersgithub.repository

import android.app.Application
import com.example.usersgithub.data.local.database.FavoriteDao
import com.example.usersgithub.data.local.database.FavoriteRoomDatabase
import com.example.usersgithub.data.local.database.FavoriteUserGithub
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import androidx.lifecycle.LiveData

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteRoomDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUserGithub> = mFavoriteDao.getFavoriteUserByUsername(username)

    fun getFavoriteUser(): LiveData<List<FavoriteUserGithub>> = mFavoriteDao.getFavoriteUser()

    fun insert(favoriteuser: FavoriteUserGithub) {
        executorService.execute { mFavoriteDao.insert(favoriteuser) }
    }

    fun delete(favoriteuser: FavoriteUserGithub) {
        executorService.execute { mFavoriteDao.delete(favoriteuser) }
    }
}