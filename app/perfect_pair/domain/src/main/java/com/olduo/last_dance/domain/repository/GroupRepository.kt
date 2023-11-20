package com.olduo.last_dance.domain.repository

import com.olduo.last_dance.domain.model.GroupDomain

interface GroupRepository {

    suspend fun createGroup(groupDomain: GroupDomain): Result<Boolean>

    suspend fun joinGroup(code: String, userId: String): Result<Boolean>

    suspend fun getUserGroups(userId: String): Result<List<GroupDomain>>
}