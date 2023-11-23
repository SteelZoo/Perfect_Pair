package com.olduo.last_dance.domain.usecase.answer

import com.olduo.last_dance.domain.model.AnswerDomain
import com.olduo.last_dance.domain.repository.AnswerRepository
import javax.inject.Inject

class GetUserAnswerByNameUsecase @Inject constructor(
    private val answerRepository: AnswerRepository
) {
    suspend operator fun invoke(qId: Int, userName: String): Result<AnswerDomain?> {
        return answerRepository.getAllAnswers(qId).getOrNull().let {list->
            if(list != null){
                Result.success(list.find { it.userName == userName })
            } else {
                Result.failure(Exception())
            }
        }
    }
}