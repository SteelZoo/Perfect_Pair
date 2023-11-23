package com.olduo.last_dance.data.repositoryImpl

import com.olduo.last_dance.data.datasource.remote.QuizRemoteDatasource
import com.olduo.last_dance.data.mapper.toDomain
import com.olduo.last_dance.data.mapper.toDto
import com.olduo.last_dance.domain.model.QuizDomain
import com.olduo.last_dance.domain.repository.QuizRepository
import javax.inject.Inject

class QuizRespositoryImpl @Inject constructor(
    private val quizRemoteDatasource: QuizRemoteDatasource
): QuizRepository {
    override suspend fun makeQuiz(quizDomain: QuizDomain): Result<Boolean>{
        return quizRemoteDatasource.makeQuiz(quizDomain.toDto());
    }

    override suspend fun getGroupQuiz(gId: Int): Result<List<QuizDomain>> {
        val res = quizRemoteDatasource.getGroupQuiz(gId).getOrNull()
        return if (res != null){
            Result.success(res.map { it.toDomain() })
        } else {
            Result.failure(Exception())
        }
    }
}