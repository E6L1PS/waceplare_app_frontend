package com.itacademy.profile.domain.usecase

import com.itacademy.common.Resource
import com.itacademy.common.model.UserInfo
import com.itacademy.profile.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend fun getUserInfoUseCase(): Flow<Resource<UserInfo?>> {
        return userRepository.getUserInfo()
    }

}