package com.olduo.last_dance.domain.repository

import com.olduo.last_dance.domain.model.AnswerDomain

interface AnswerRepository {
    suspend fun sendAnswer(answerDomain: AnswerDomain): Result<Boolean>

    suspend fun updateAnswer(answerDomain: AnswerDomain): Result<Boolean>

    suspend fun getAllAnswers(qId: Int): Result<List<AnswerDomain>>

    suspend fun getUserAnswer(qId: Int, userId: String): Result<AnswerDomain>
}