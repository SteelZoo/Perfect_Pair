package com.olduo.last_dance.data.datasource.remote.service

import com.olduo.last_dance.data.model.QuizDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuizSevice {
    @GET("quiz/{gId}")
    suspend fun getGroupQuiz(@Path("gId") gId: Int): Response<List<QuizDto>>

    @POST("quiz")
    suspend fun makeQuiz(@Body quizDto: QuizDto): Response<Boolean>
}