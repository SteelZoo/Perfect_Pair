package com.olduo.last_dance.preseatation.mapper

import com.olduo.last_dance.domain.model.UserDomain
import com.olduo.last_dance.preseatation.model.User

fun UserDomain.toPresentation(): User{
    return User(id,name)
}