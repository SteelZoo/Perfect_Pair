package com.olduo.last_dance.data.repositoryImpl

import com.olduo.last_dance.data.datasource.remote.GroupRemoteDatasource
import com.olduo.last_dance.data.mapper.toGroupDomain
import com.olduo.last_dance.data.mapper.toGroupDto
import com.olduo.last_dance.domain.model.GroupDomain
import com.olduo.last_dance.domain.repository.GroupRepository
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupRemoteDatasource: GroupRemoteDatasource
) : GroupRepository {
    override suspend fun createGroup(groupDomain: GroupDomain): Result<Boolean> {
        return groupRemoteDatasource.craateGroup(groupDomain.toGroupDto())
    }

    override suspend fun joinGroup(code: String, userId: String): Result<Boolean> {
        return groupRemoteDatasource.joinGroup(code, userId)
    }

    override suspend fun getUserGroups(userId: String): Result<List<GroupDomain>> {
        val res = groupRemoteDatasource.getUserGroups(userId)
        if (res.isSuccess) {
            val list = res.getOrElse { return Result.failure(Exception()) }
            return Result.success(list.map { it.toGroupDomain() })
        } else {
            return Result.failure(Exception())
        }
    }
}