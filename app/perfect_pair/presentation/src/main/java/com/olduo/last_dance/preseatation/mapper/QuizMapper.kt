package com.olduo.last_dance.preseatation.mapper

import com.olduo.last_dance.domain.model.QuizDomain
import com.olduo.last_dance.preseatation.model.GameSet
import com.olduo.last_dance.preseatation.model.Question

fun GameSet.toQuizDomain(): QuizDomain {
    return QuizDomain(
        id,
        gId,
        title,
        questionListToRawString(questionList)
    )
}

fun QuizDomain.toGameSet(): GameSet {
    return GameSet(
        id,
        gId,
        title,
        rawStringToQusstionList(question)
    )
}

private fun rawStringToQusstionList(rawString: String): List<Question> {
    return rawString.split("/").map {
        it.split("^").let {
            Question(it[0], it[1])
        }
    }
}

private fun questionListToRawString(list: List<Question>): String {
    val sb = StringBuilder()
    list.forEachIndexed { index, question ->
        sb.append("${question.first}^${question.second}")
        if (index != list.size - 1) {
            sb.append("/")
        }
    }
    return sb.toString()
}