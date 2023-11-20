package com.olduo.last_dance.data.datasource.remote

import com.olduo.last_dance.data.datasource.remote.service.GroupService
import com.olduo.last_dance.data.datasource.util.toResult
import com.olduo.last_dance.data.model.GroupDto
import javax.inject.Inject

class GroupRemoteDatasource @Inject constructor(
    private val groupService: GroupService
) {
    suspend fun craateGroup(groupDto: GroupDto): Result<Boolean>{
        return groupService.craeteGroup(groupDto,groupDto.creator).toResult()
    }

    suspend fun joinGroup(code: String, userId: String): Result<Boolean>{
        return groupService.joinGroup(code, userId).toResult()
    }

    suspend fun getUserGroups(userId: String): Result<List<GroupDto>>{
        return groupService.getUserGroups(userId).toResult()
    }

}