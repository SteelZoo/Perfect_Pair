package com.olduo.last_dance.data.repositoryImpl

import com.olduo.last_dance.domain.model.UserDomain
import com.olduo.last_dance.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(

): UserRepository {
    override suspend fun signUp(id: String, pass: String, name: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(id: String, pass: String): UserDomain? {
        TODO("Not yet implemented")
    }

    override suspend fun isDupId(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun autoLogin(id: String): UserDomain? {
        TODO("Not yet implemented")
    }
}