package com.olduo.last_dance.domain.repository

import com.olduo.last_dance.domain.model.QuizDomain

interface QuizRepository {
    suspend fun makeQuiz(quizDomain: QuizDomain): Result<Boolean>

    suspend fun getGroupQuiz(gId: Int): Result<List<QuizDomain>>
}