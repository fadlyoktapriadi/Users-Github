package com.example.usersgithub.data

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.usersgithub.data.api.ApiService
import com.example.usersgithub.data.api.response.DetailUserResponse
import com.example.usersgithub.data.api.response.FollowResponseItem
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.data.local.database.FavoriteDao
import com.example.usersgithub.data.local.entity.FavoriteUserGithub
import com.example.usersgithub.domain.model.UserFav
import com.example.usersgithub.utils.AppExecutors
import com.example.usersgithub.utils.DataMapper


class UsersRepository private constructor(
    private val apiService: ApiService,
    private val mFavoriteDao: FavoriteDao,
    private val appExecutors: AppExecutors
) {

    fun getUsers(): LiveData<Result<List<UserGithub>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers("fadly")
            emit(Result.Success(response.items))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getSearch(query: String): LiveData<Result<List<UserGithub>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers(query)
            emit(Result.Success(response.items))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getDetailUser(username: String): LiveData<Result<DetailUserResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getDetailUser(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getFollowing(username: String): LiveData<Result<List<FollowResponseItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFollowing(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getFollowers(username: String): LiveData<Result<List<FollowResponseItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getFollowers(username)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error("${e.message}"))
        }
    }

    fun getFavorite(): LiveData<List<FavoriteUserGithub>> = mFavoriteDao.getFavoriteUser()

    fun getFavoriteByLogin(login: String): LiveData<UserFav> =
        mFavoriteDao.getFavoriteUserByLogin(login)

//    fun insertFavorite(favoriteuser: FavoriteUserGithub) {
//        appExecutors.diskIO.execute { mFavoriteDao.insert(favoriteuser) }
//    }
//
//    fun deleteFavorite(favoriteuser: FavoriteUserGithub) {
//        appExecutors.diskIO.execute { mFavoriteDao.delete(favoriteuser) }
//    }

    //    fun getFavoriteUser(): LiveData<List<UserFav>> {
//        return Transformation.map(mFavoriteDao.getFavoriteUser()) {
//            DataMapper.mapEntitiesToDomain(it)
//        }
//    }


    fun setFavorite(userfav: UserFav) {
        val favEntity = DataMapper.mapDomainToEntity(userfav)
        appExecutors.diskIO.execute { mFavoriteDao.insert(favEntity) }
    }

    fun deleteFavorite(userfav: UserFav) {
        val favEntity = DataMapper.mapDomainToEntity(userfav)
        appExecutors.diskIO.execute { mFavoriteDao.delete(favEntity) }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: UsersRepository? = null
        fun getInstance(
            apiService: ApiService,
            favoriteDao: FavoriteDao,
            appExecutors: AppExecutors
        ): UsersRepository =
            instance ?: synchronized(this) {
                instance ?: UsersRepository(apiService, favoriteDao, appExecutors)
            }.also { instance = it }
    }
}