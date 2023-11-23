package com.olduo.last_dance.domain.model

data class GroupDomain(
    val id: Int,
    val title: String,
    val description: String,
    val creator: String,
    val code: String
){
    constructor(title: String, description: String,creator: String) : this(0, title, description, creator, "")
}