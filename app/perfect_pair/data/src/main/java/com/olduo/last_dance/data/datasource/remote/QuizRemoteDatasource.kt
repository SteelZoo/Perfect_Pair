package com.olduo.last_dance.data.datasource.remote

import android.util.Log
import com.olduo.last_dance.data.datasource.remote.service.QuizSevice
import com.olduo.last_dance.data.datasource.util.toResult
import com.olduo.last_dance.data.model.QuizDto
import javax.inject.Inject

class QuizRemoteDatasource @Inject constructor(
    private val quizSevice: QuizSevice
) {
    suspend fun makeQuiz(quizDto: QuizDto): Result<Boolean>{
        Log.d("TAG", "makeQuiz: ${quizDto}")
        return quizSevice.makeQuiz(quizDto).also {
            Log.d("TAG", "makeQuiz: ${it.code()}\n ${it.message()}")
        }.toResult()
    }

    suspend fun getGroupQuiz(gId: Int): Result<List<QuizDto>>{
        return quizSevice.getGroupQuiz(gId).toResult()
    }
}