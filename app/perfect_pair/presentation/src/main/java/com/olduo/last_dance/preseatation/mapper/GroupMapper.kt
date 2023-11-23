package com.olduo.last_dance.preseatation.mapper

import com.olduo.last_dance.domain.model.GroupDomain
import com.olduo.last_dance.preseatation.model.Group

fun GroupDomain.toPresentation(): Group{
    return Group(
        id, title, description, creator, code
    )
}

fun Group.toDomain(): GroupDomain{
    return GroupDomain(
        id, title, description, creator, code
    )
}