package com.example.core.data.api

import com.example.core.data.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result: Flow<Result<ResultType>> = flow {
        emit(Result.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                emitAll(loadFromNetwork(apiResponse.data).map {
                    Result.Success(it)
                })
            }

            is ApiResponse.Error -> {
                emit(Result.Error<ResultType>(apiResponse.errorMessage))
            }
        }
    }

    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Result<ResultType>> = result
}