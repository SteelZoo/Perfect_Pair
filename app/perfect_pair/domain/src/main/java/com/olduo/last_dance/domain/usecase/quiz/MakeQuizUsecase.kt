package com.olduo.last_dance.domain.usecase.quiz

import com.olduo.last_dance.domain.model.QuizDomain
import com.olduo.last_dance.domain.repository.QuizRepository
import java.util.AbstractQueue
import javax.inject.Inject

class MakeQuizUsecase @Inject constructor(
    private val quizRepository: QuizRepository
) {
    suspend operator fun invoke(quizDomain: QuizDomain): Result<Boolean>{
        return quizRepository.makeQuiz(quizDomain)
    }
}