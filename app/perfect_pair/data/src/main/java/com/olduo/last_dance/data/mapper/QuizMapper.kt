package com.olduo.last_dance.data.mapper

import com.olduo.last_dance.data.model.QuizDto
import com.olduo.last_dance.domain.model.QuizDomain

fun QuizDto.toDomain(): QuizDomain{
    return QuizDomain(
        id, gId, title, question
    )
}

fun QuizDomain.toDto(): QuizDto{
    return QuizDto(
        id, gId, title, question
    )
}