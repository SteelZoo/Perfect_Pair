package com.olduo.last_dance.domain.model

data class AnswerDomain(
    val id: Int,
    val qId: Int,
    val userId: String,
    val userName: String,
    val answer: String
)