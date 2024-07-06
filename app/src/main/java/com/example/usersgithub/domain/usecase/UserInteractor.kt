package com.example.usersgithub.domain.usecase

import com.example.usersgithub.data.Result
import com.example.usersgithub.domain.model.User
import com.example.usersgithub.domain.repository.IUserFavRepository
import kotlinx.coroutines.flow.Flow

class UserInteractor (private val usersRepository: IUserFavRepository): UserUseCase  {

    override fun getUsers(query: String?): Flow<Result<List<User>>> = usersRepository.getUsers(query)

    override fun getFollowers(username: String): Flow<Result<List<User>>> = usersRepository.getFollowers(username)

    override fun getFollowing(username: String): Flow<Result<List<User>>> = usersRepository.getFollowing(username)

    override fun getDetailUser(username: String): Flow<Result<User>> = usersRepository.getDetailUser(username)

    override fun getFavoriteUser(): Flow<List<User>> = usersRepository.getFavoriteUser()

    override fun getDetail(username: String): Flow<User>? = usersRepository.getDetail(username)

    override suspend fun insertUserFav(user: User) = usersRepository.insertUserFav(user)

    override suspend fun deleteUserFav(user: User): Int = usersRepository.deleteUserFav(user)
}