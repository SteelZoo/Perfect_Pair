package com.olduo.last_dance.domain.usecase.user

import com.olduo.last_dance.domain.model.UserDomain
import com.olduo.last_dance.domain.repository.UserRepository
import javax.inject.Inject

class SignInUsecase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String, pass: String): UserDomain?{
        return userRepository.signIn(id, pass)
    }

    suspend fun autoLotin(id: String): UserDomain?{
        return userRepository.autoLogin(id)
    }
}