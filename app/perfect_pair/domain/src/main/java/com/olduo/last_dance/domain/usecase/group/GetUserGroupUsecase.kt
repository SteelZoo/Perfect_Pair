package com.olduo.last_dance.domain.usecase.group

import com.olduo.last_dance.domain.model.GroupDomain
import com.olduo.last_dance.domain.repository.GroupRepository
import javax.inject.Inject

class GetUserGroupUsecase @Inject constructor(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(userId: String): Result<List<GroupDomain>>{
        return groupRepository.getUserGroups(userId)
    }
}