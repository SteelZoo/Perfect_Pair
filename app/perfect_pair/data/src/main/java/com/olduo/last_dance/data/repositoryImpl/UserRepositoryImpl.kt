package com.olduo.last_dance.data.repositoryImpl

import com.olduo.last_dance.data.datasource.remote.UserRemoteDatasource
import com.olduo.last_dance.data.mapper.toDomain
import com.olduo.last_dance.data.model.UserDto
import com.olduo.last_dance.domain.model.UserDomain
import com.olduo.last_dance.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDatasource: UserRemoteDatasource
): UserRepository {
    override suspend fun signUp(id: String, pass: String, name: String): Boolean {
        return userRemoteDatasource.signUp(UserDto(id, pass, name))
    }

    override suspend fun signIn(id: String, pass: String): UserDomain? {
        return userRemoteDatasource.signIn(id, pass)?.toDomain()
    }

    override suspend fun isDupId(id: String): Result<Boolean> {
        return userRemoteDatasource.checkDup(id)
    }

    override suspend fun autoLogin(id: String): UserDomain? {
        return userRemoteDatasource.autoSignIn(id)?.toDomain()
    }
}