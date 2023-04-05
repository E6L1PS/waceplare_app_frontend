package com.itacademy.waceplare.glue.signin.repository

import com.itacademy.common.Resource
import com.itacademy.data.AuthDataRepository
import com.itacademy.common.model.AuthResult
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.model.RegisterRequest
import com.itacademy.sign_in.domain.repository.AuthRepository
import javax.inject.Inject


class AdapterAuthRepository @Inject constructor(
    private val authDataRepository: AuthDataRepository
): AuthRepository {
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