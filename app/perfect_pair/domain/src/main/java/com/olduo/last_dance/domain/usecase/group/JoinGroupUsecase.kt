package com.olduo.last_dance.domain.usecase.group

import com.olduo.last_dance.domain.repository.GroupRepository
import javax.inject.Inject

class JoinGroupUsecase @Inject constructor(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(code: String, userId: String): Result<Boolean>{
        return groupRepository.joinGroup(code, userId)
    }
}