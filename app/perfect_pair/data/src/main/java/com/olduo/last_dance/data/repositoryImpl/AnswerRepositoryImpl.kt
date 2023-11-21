package com.olduo.last_dance.data.repositoryImpl

import com.olduo.last_dance.data.datasource.remote.AnswerRemoteDatasource
import com.olduo.last_dance.data.mapper.toDomain
import com.olduo.last_dance.data.mapper.toDto
import com.olduo.last_dance.domain.model.AnswerDomain
import com.olduo.last_dance.domain.repository.AnswerRepository
import javax.inject.Inject

class AnswerRepositoryImpl @Inject constructor(
    private val answerRemoteDatasource: AnswerRemoteDatasource
) : AnswerRepository {

    override suspend fun sendAnswer(answerDomain: AnswerDomain): Result<Boolean> {
        return answerRemoteDatasource.sendAnswer(answerDomain.toDto())
    }

    override suspend fun updateAnswer(answerDomain: AnswerDomain): Result<Boolean> {
        return answerRemoteDatasource.updateAnswer(answerDomain.toDto())
    }

    override suspend fun getAllAnswers(qId: Int): Result<List<AnswerDomain>> {
        return answerRemoteDatasource.getAllAnswers(qId).getOrNull().let { list ->
            if (list != null) {
                Result.success(list.map { it.toDomain() })
            } else {
                Result.failure(Exception())
            }
        }
    }

    override suspend fun getUserAnswer(qId: Int, userId: String): Result<AnswerDomain> {
        return answerRemoteDatasource.getUserAnswer(qId, userId).getOrNull().let {
            if (it != null){
                Result.success(it.toDomain())
            } else {
                Result.failure(Exception())
            }
        }
    }
}