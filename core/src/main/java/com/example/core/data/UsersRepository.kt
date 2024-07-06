package com.example.core.data

import com.example.core.data.api.ApiResponse
import com.example.core.data.api.NetworkOnlyResource
import com.example.core.data.api.RemoteDataSource
import com.example.core.data.api.response.UserGithub
import com.example.core.data.local.LocalDataSource
import com.example.core.domain.model.User
import com.example.core.domain.repository.IUserFavRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UsersRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IUserFavRepository {

    override fun getUsers(query: String?): Flow<Result<List<User>>> {
        return object : NetworkOnlyResource<List<User>, List<UserGithub>>() {
            override fun loadFromNetwork(data: List<UserGithub>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserGithub>>> {
                return remoteDataSource.getUsers(query)
            }

        }.asFlow()
    }

    override fun getFollowers(username: String): Flow<Result<List<User>>> {
        return object : NetworkOnlyResource<List<User>, List<UserGithub>>() {
            override fun loadFromNetwork(data: List<UserGithub>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserGithub>>> {
                return remoteDataSource.getFollowers(username)
            }

        }.asFlow()
    }

    override fun getFollowing(username: String): Flow<Result<List<User>>> {
        return object : NetworkOnlyResource<List<User>, List<UserGithub>>() {
            override fun loadFromNetwork(data: List<UserGithub>): Flow<List<User>> {
                return DataMapper.mapResponsesToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserGithub>>> {
                return remoteDataSource.getFollowing(username)
            }
        }.asFlow()
    }

    override fun getDetailUser(username: String): Flow<Result<User>> {
        return object : NetworkOnlyResource<User, UserGithub>() {
            override fun loadFromNetwork(data: UserGithub): Flow<User> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserGithub>> {
                return remoteDataSource.getDetail(username)
            }

        }.asFlow()
    }

    override fun getFavoriteUser(): Flow<List<User>> {
        return localDataSource.getUserFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getDetail(username: String): Flow<User>? {
        return localDataSource.getDetail(username)?.map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override suspend fun insertUserFav(user: User) {
        val domainUser = DataMapper.mapDomainToEntity(user)
        return localDataSource.insertUserFav(domainUser)
    }

    override suspend fun deleteUserFav(user: User): Int {
        val domainUser = DataMapper.mapDomainToEntity(user)
        return localDataSource.deleteUserFav(domainUser)
    }
}
