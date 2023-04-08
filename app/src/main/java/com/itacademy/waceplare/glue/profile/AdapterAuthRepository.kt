package com.itacademy.waceplare.glue.profile

import com.itacademy.common.Resource
import com.itacademy.data.AuthDataRepository
import com.itacademy.profile.domain.repository.AuthRepository
import javax.inject.Inject

class AdapterAuthRepository @Inject constructor(
    private val authDataRepository: AuthDataRepository
) : AuthRepository {

    override suspend fun isAuthenticated(): Resource<Unit> {
        return authDataRepository.isAuthenticated()
    }

    override suspend fun logOut() {
        authDataRepository.logOut()
    }

}