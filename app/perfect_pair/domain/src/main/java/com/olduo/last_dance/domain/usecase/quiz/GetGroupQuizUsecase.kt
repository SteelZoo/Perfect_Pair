package com.olduo.last_dance.domain.usecase.quiz

import com.olduo.last_dance.domain.model.QuizDomain
import com.olduo.last_dance.domain.repository.QuizRepository
import javax.inject.Inject

class GetGroupQuizUsecase @Inject constructor(
    private val quizRepository: QuizRepository
) {
    suspend operator fun invoke(gId: Int): Result<List<QuizDomain>>{
        return quizRepository.getGroupQuiz(gId)
    }
}