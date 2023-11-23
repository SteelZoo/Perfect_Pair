package com.olduo.last_dance.data.mapper

import android.icu.text.RelativeDateTimeFormatter.AbsoluteUnit
import com.olduo.last_dance.data.model.AutoLoginDto
import com.olduo.last_dance.data.model.UserDto
import com.olduo.last_dance.domain.model.UserDomain

fun UserDto.toDomain(): UserDomain{
    return UserDomain(userId, pass, name)
}

fun AutoLoginDto.toUserDto(): UserDto{
    return UserDto(
        this.user.userId!!,
        this.user.pass!!,
        this.user.name!!
    )
}
