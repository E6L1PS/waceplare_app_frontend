package com.itacademy.waceplare.glue.auth.repository

import com.itacademy.auth.domain.repository.AuthRepository
import com.itacademy.common.Resource
import com.itacademy.data.AuthDataRepository
import com.itacademy.common.model.AuthenticationRequest
import com.itacademy.common.model.RegisterRequest
import javax.inject.Inject


class AdapterAuthRepository @Inject constructor(
    private val authDataRepository: AuthDataRepository,
) : AuthRepository {
    override suspend fun signIn(authenticationRequest: AuthenticationRequest): Resource<Unit> {
        return authDataRepository.signIn(authenticationRequest)
    }

    override suspend fun signUp(registerRequest: RegisterRequest): Resource<Unit> {
        return authDataRepository.signUp(registerRequest)
    }

    override suspend fun isAuthenticated(): Resource<Unit> {
        return authDataRepository.isAuthenticated()
    }


}