package com.olduo.last_dance.preseatation.model

data class Answer(
    val id: Int,
    val userId: String,
    val userName: String,
    val qId: Int,
    val answerList: List<Int>
)