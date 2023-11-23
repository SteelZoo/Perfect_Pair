package com.olduo.last_dance.preseatation.model

data class GameSet(
    val id: Int,
    val gId: Int,
    val title: String,
    val questionList: List<Question>
)

data class Question(
    val first: String,
    val second: String
)