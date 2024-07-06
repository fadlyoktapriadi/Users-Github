package com.example.core.data.api

sealed class ApiResponse<out T> {
    data class Success<out T> (val data: T): ApiResponse<T>()
    data class Error(val errorMessage: String?): ApiResponse<Nothing>()
}