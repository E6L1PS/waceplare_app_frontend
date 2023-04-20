package com.itacademy.waceplare.glue.profile

import com.itacademy.common.Resource
import com.itacademy.common.model.UserInfo
import com.itacademy.data.UserDataRepository
import com.itacademy.profile.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AdapterUserRepository @Inject constructor(
    private val userDataRepository: UserDataRepository
): UserRepository {

    override suspend fun getUserInfo(): Flow<Resource<UserInfo?>> {
        return userDataRepository.getUserInfo()
    }

}
