package com.olduo.last_dance.data.datasource.remote.service

import com.olduo.last_dance.data.model.AutoLoginDto
import com.olduo.last_dance.data.model.UserDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface UserService {

    /**
     * 실패하면 에러 반환
     */
    @POST("user")
    suspend fun signUp(@Body userDto: UserDto): Response<Boolean>

    /**
     * 실패하면 dto의 변수가 null
     */
    @GET("user/info")
    suspend fun autoSignIn(@Query("userId") userId: String): Response<AutoLoginDto>

    /**
     * 실패하면 반환 json이 없음
     */
    @POST("user/login")
    suspend fun signIn(@Body userDto: UserDto): Response<UserDto>

    @GET("user/isUsed")
    suspend fun checkDup(@Query("userId") userId: String): Response<Boolean>

}