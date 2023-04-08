package com.itacademy.profile.domain.usecase

import com.itacademy.common.Resource
import com.itacademy.profile.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun logOut() {
        authRepository.logOut()
    }

    suspend fun isAuthenticated(): Resource<Unit> {
        return authRepository.isAuthenticated()
    }


}
