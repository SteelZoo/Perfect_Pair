package com.olduo.last_dance.domain.usecase.answer

import com.olduo.last_dance.domain.model.AnswerDomain
import com.olduo.last_dance.domain.repository.AnswerRepository
import javax.inject.Inject

class GetAllUsersAnswerUsecase @Inject constructor(
    private val answerRepository: AnswerRepository
) {
    suspend operator fun invoke(qId: Int): Result<List<AnswerDomain>> {
        return answerRepository.getAllAnswers(qId)
    }
}