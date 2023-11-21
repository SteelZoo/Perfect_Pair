package com.olduo.last_dance.data.mapper

import com.olduo.last_dance.data.model.AnswerDto
import com.olduo.last_dance.domain.model.AnswerDomain

fun AnswerDto.toDomain(): AnswerDomain{
    return AnswerDomain(
        id, qId, userId, userName, answer
    )
}

fun AnswerDomain.toDto(): AnswerDto{
    return AnswerDto(
        id, qId, userId, userName, answer
    )
}