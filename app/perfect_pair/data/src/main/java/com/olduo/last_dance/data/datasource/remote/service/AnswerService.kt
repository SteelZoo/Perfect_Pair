package com.olduo.last_dance.data.datasource.remote.service

import com.olduo.last_dance.data.model.AnswerDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AnswerService {
    @POST("answer")
    suspend fun sendAnswer(@Body answerDto: AnswerDto): Response<Boolean>

    @PUT("answer")
    suspend fun updateAnswer(@Body answerDto: AnswerDto): Response<Boolean>

    @GET("answer/{qId}")
    suspend fun getAllAnswers(@Path("qId") qId: Int): Response<List<AnswerDto>>

    @GET("answer/{qId}/{userId}")
    suspend fun getUserAnswer(@Path("qId") qId: Int, @Path("userId") userId: String): Response<AnswerDto>
}