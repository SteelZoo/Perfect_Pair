package com.olduo.last_dance.domain.usecase.user

import com.olduo.last_dance.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUsecase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String, pass: String, name: String): Boolean{
        return userRepository.signUp(id, pass, name)
    }

    suspend fun checkDup(id: String): Result<Boolean>{
        return userRepository.isDupId(id)
    }
}