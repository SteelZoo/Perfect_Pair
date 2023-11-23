package com.olduo.last_dance.preseatation.util

import com.olduo.last_dance.preseatation.model.Answer
import com.olduo.last_dance.preseatation.model.MatchScore
import com.olduo.last_dance.preseatation.model.User

object ScoreUtil {
    fun getScore(firstUserAnswer: Answer, secondUserAnswer: Answer): MatchScore {
        val totalCount = firstUserAnswer.answerList.size
        var correctCount = 0
        for (i in 0 until totalCount) {
            if (firstUserAnswer.answerList[i] == secondUserAnswer.answerList[i]) {
                correctCount++
            }
        }

        val score = if (correctCount == totalCount) {
            100
        } else if (correctCount == 0) {
            0
        } else {
            100 / totalCount * correctCount
        }

        return MatchScore(
            User(firstUserAnswer.userId, firstUserAnswer.userName),
            User(secondUserAnswer.userId, secondUserAnswer.userName),
            score
        )
    }

    fun getAllMatchingScores(answerList: List<Answer>): List<MatchScore> {
        val result = mutableListOf<MatchScore>()

        for (i in 0 until answerList.size - 1) {
            for (j in i + 1 until answerList.size) {
                result.add(getScore(answerList[i], answerList[j]))
            }
        }

        return result
    }

    fun getScoresWithUsers(userId: String, answerList: List<Answer>): List<MatchScore> {
        val result = mutableListOf<MatchScore>()

        for (i in 0 until answerList.size - 1) {
            for (j in i + 1 until answerList.size) {
                if (answerList[i].userId == userId || answerList[j].userId == userId) {
                    result.add(getScore(answerList[i], answerList[j]))
                }
            }
        }

        return result.map {
            if (it.secondUser.id == userId){
                MatchScore(
                    it.secondUser,
                    it.firstUser,
                    it.score
                )
            }else {
                it
            }
        }
    }
}