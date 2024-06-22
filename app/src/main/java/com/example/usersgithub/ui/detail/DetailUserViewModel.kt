package com.example.usersgithub.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.api.response.DetailUserResponse
import com.example.usersgithub.data.api.response.FollowingFollowersResponseItem
import com.example.usersgithub.data.api.retrofit.ApiConfig
import com.example.usersgithub.data.local.database.FavoriteUserGithub
import com.example.usersgithub.repository.FavoriteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(application: Application) : ViewModel() {

    private val mApplication: FavoriteRepository =
        FavoriteRepository(application)

    private val _detailUserGithub = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUserGithub

    private val _followingResponseItem = MutableLiveData<List<FollowingFollowersResponseItem>>()
    val followingResponseItem: LiveData<List<FollowingFollowersResponseItem>> =
        _followingResponseItem

    private val _followersResponseItem = MutableLiveData<List<FollowingFollowersResponseItem>>()
    val followersResponseItem: LiveData<List<FollowingFollowersResponseItem>> =
        _followersResponseItem

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

//    fun getFavoriteUserByUsername(username : String) {
//        mApplication.getFavoriteUserByUsername(username)
//    }

    fun getFavoriteUserByUsername(username : String): LiveData<FavoriteUserGithub> =
        mApplication.getFavoriteUserByUsername(username)

    fun insert(favorite: FavoriteUserGithub) {
        mApplication.insert(favorite)
    }

    fun delete(favorite: FavoriteUserGithub) {
        mApplication.delete(favorite)
    }

    var username: String = ""
        set(value) {
            field = value
            detailUser()
        }

    private fun detailUser() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getDetailUser(username)

        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailUserGithub.value = responseBody!!
                    }
                } else {
                    Log.e(TAG, "onFailure Detail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowing(userx: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getFollowing(userx)

        client.enqueue(object : Callback<List<FollowingFollowersResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowingFollowersResponseItem>>,
                response: Response<List<FollowingFollowersResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _isLoading.value = false
                        _followingResponseItem.value = responseBody!!
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowingFollowersResponseItem>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure Following: ${t.message}")
            }
        })
    }

    fun getFollowers(userx: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getFollowers(userx)

        client.enqueue(object : Callback<List<FollowingFollowersResponseItem>> {
            override fun onResponse(
                call: Call<List<FollowingFollowersResponseItem>>,
                response: Response<List<FollowingFollowersResponseItem>>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _isLoading.value = false
                        _followersResponseItem.value = responseBody!!
                    }
                } else {
                    Log.e(TAG, "onFailure Followers: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<FollowingFollowersResponseItem>>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailUserViewModel"
    }
}