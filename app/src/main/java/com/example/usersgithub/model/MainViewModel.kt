package com.example.usersgithub.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usersgithub.data.api.response.GithubResponse
import com.example.usersgithub.data.api.response.UserGithub
import com.example.usersgithub.data.api.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _githubresponse = MutableLiveData<GithubResponse>()
    val users: LiveData<GithubResponse> = _githubresponse

    private val _listUserGithub = MutableLiveData<List<UserGithub>>()
    val listUser: LiveData<List<UserGithub>> = _listUserGithub

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        findUsers()
    }

    private fun findUsers() {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUsers(USERNAME)

        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUserGithub.value = response.body()?.items as List<UserGithub>?
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun findUsersBySearch(userSearch: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUsers(userSearch)

        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _listUserGithub.value = response.body()?.items as List<UserGithub>?
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = true
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "MainViewModel"
        private const val USERNAME = "fadly"
    }
}