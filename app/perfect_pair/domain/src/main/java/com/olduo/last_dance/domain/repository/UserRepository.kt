package com.olduo.last_dance.domain.repository

import com.olduo.last_dance.domain.model.UserDomain

interface UserRepository {
    suspend fun signUp(id: String, pass: String, name: String): Boolean

    suspend fun signIn(id: String,pass: String): UserDomain?

    suspend fun isDupId(id: String): Result<Boolean>

    suspend fun autoLogin(id: String): UserDomain?
}