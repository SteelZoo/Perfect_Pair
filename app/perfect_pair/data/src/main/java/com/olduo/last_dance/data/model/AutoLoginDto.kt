package com.olduo.last_dance.data.model

data class AutoLoginDto(
    val user: NullableUser
){
    data class NullableUser(
        val userId: String?,
        val name: String?,
        val pass: String?
    )
}

