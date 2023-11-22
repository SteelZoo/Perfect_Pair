package com.olduo.last_dance.data.datasource.remote

import android.accounts.NetworkErrorException
import android.util.Log
import com.olduo.last_dance.data.datasource.remote.service.UserService
import com.olduo.last_dance.data.mapper.toUserDto
import com.olduo.last_dance.data.model.UserDto
import javax.inject.Inject

class UserRemoteDatasource @Inject constructor(
    private val userService: UserService
) {
    suspend fun signIn(id: String, pass: String): UserDto? {
        val res = kotlin.runCatching {
            userService.signIn(UserDto(id, pass, ""))
        }.getOrElse {
            return null
        }
        return res.body()
    }

    suspend fun signUp(userDto: UserDto): Boolean {
        val res = kotlin.runCatching {
            userService.signUp(userDto)
        }.getOrElse {
            return false
        }

        return if (res.isSuccessful && res.body() != null) {
            res.body()!!
        } else {
            false
        }
    }

    suspend fun autoSignIn(userId: String): UserDto? {
        Log.d("TAG", "autoSignInDATA: ")
        val res = kotlin.runCatching {
            userService.autoSignIn(userId).also {
                Log.d("TAG", "autoSignIn: ${it.body()}")
            }
        }.getOrElse {
            Log.d("TAG", "autoSignIn: ${it.message}")
            return null
        }

        return if (res.isSuccessful && res.body() != null && res.body()!!.user.userId != null) {
            res.body()!!.toUserDto()
        } else {
            null
        }
    }

    suspend fun checkDup(userId: String): Result<Boolean> {
        val res = kotlin.runCatching {
            userService.checkDup(userId)
        }.getOrElse { return Result.failure(NetworkErrorException()) }

        if (res.isSuccessful && res.body() != null){
            return Result.success(res.body()!!)
        } else {
            return Result.failure(NetworkErrorException())
        }
    }
}