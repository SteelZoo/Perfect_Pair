package com.olduo.last_dance.domain.usecase.answer

import com.olduo.last_dance.domain.model.AnswerDomain
import com.olduo.last_dance.domain.repository.AnswerRepository
import javax.inject.Inject

class GetUserAnswerByIdUsecase @Inject constructor(
    private val answerRepository: AnswerRepository
) {
    suspend operator fun invoke(qId: Int, userId: String): Result<AnswerDomain?> {
        return answerRepository.getUserAnswer(qId,userId)
    }
}