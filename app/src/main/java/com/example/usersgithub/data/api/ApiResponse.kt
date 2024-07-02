package com.example.usersgithub.data.api

sealed class ApiResponse<out T> {
    data class Success<out T> (val data: T): ApiResponse<T>()
    data class Error(val errorMessage: String?): ApiResponse<Nothing>()
}