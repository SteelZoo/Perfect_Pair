package com.olduo.last_dance.data.datasource.remote

import com.olduo.last_dance.data.datasource.remote.service.AnswerService
import com.olduo.last_dance.data.datasource.util.toResult
import com.olduo.last_dance.data.model.AnswerDto
import javax.inject.Inject

class AnswerRemoteDatasource @Inject constructor(
    private val answerService: AnswerService
) {
    suspend fun sendAnswer(answerDto: AnswerDto): Result<Boolean>{
        return answerService.sendAnswer(answerDto).toResult()
    }

    suspend fun updateAnswer(answerDto: AnswerDto): Result<Boolean>{
        return answerService.updateAnswer(answerDto).toResult()
    }

    suspend fun getAllAnswers(qId: Int): Result<List<AnswerDto>>{
        return answerService.getAllAnswers(qId).toResult()
    }

    suspend fun getUserAnswer(qId: Int, userId: String): Result<AnswerDto>{
        return answerService.getUserAnswer(qId, userId).toResult()
    }

}