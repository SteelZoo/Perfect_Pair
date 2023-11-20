package com.olduo.last_dance.data.mapper

import com.olduo.last_dance.data.model.UserDto
import com.olduo.last_dance.domain.model.UserDomain

fun UserDto.toDomain(): UserDomain{
    return UserDomain(userId, pass, name)
}
