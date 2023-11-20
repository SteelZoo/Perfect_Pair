package com.olduo.last_dance.data.mapper

import com.olduo.last_dance.data.model.GroupDto
import com.olduo.last_dance.domain.model.GroupDomain

fun GroupDto.toGroupDomain(): GroupDomain{
    return GroupDomain(
        id, title, description, creator, code
    )
}

fun GroupDomain.toGroupDto(): GroupDto{
    return GroupDto(
        id, title, description, creator, code
    )
}