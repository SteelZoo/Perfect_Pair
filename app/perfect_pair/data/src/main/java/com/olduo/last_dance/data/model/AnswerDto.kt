package com.olduo.last_dance.data.model

import com.squareup.moshi.Json

data class AnswerDto(
    @Json(name = "answerId") val id: Int,
    val qId: Int,
    val userId: String,
    val userName: String,
    val answer: String
)