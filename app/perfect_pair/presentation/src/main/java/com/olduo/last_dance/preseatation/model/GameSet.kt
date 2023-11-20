package com.olduo.last_dance.preseatation.model

data class GameSet(
    val id: String,
    val gId: String,
    val questionList: List<Question>
)

data class Question(
    val first: String,
    val second: String
)