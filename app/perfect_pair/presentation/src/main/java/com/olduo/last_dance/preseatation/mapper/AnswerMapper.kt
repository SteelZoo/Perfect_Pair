package com.olduo.last_dance.preseatation.mapper

import com.olduo.last_dance.domain.model.AnswerDomain
import com.olduo.last_dance.preseatation.model.Answer

fun AnswerDomain.toPresentation(): Answer{
    return Answer(
        id, userId, userName, qId, answer.split(",").map { it.toInt() }
    )
}

fun Answer.toDomain(): AnswerDomain{
    return AnswerDomain(
        id, qId,userId,userName, answerListToRawString(answerList)
    )
}

fun answerListToRawString(list: List<Int>): String {
    val stringBuilder = StringBuilder()

    list.forEachIndexed { index, i ->
        stringBuilder.append(i)
        if (index != list.size - 1) {
            stringBuilder.append(",")
        }
    }

    return stringBuilder.toString()
}