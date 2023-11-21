package com.olduo.last_dance.data.model

import com.squareup.moshi.Json

data class QuizDto(
    @Json(name = "id") val id: Int,
    @Json(name = "gId") val gId: Int,
    @Json(name = "title") val title: String,
    @Json(name = "question") val question: String
)