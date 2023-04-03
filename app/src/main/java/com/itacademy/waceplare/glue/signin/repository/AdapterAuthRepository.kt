package com.itacademy.waceplare.glue.signin.repository

import com.itacademy.data.AuthDataRepository
import com.itacademy.common.model.AuthResult
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.model.RegisterRequest
import com.itacademy.sign_in.domain.repository.AuthRepository
import javax.inject.Inject


class AdapterAuthRepository @Inject constructor(
    private val authDataRepository: AuthDataRepository
): AuthRepository {
    override suspend fun signIn(authenticationRequest: AuthenticationRequest): AuthResult<Unit> {
        return authDataRepository.signIn(authenticationRequest)
    }

    override suspend fun signUp(registerRequest: RegisterRequest): AuthResult<Unit> {
        return authDataRepository.signUp(registerRequest)
    }

    override suspend fun isAuthenticated(): AuthResult<Unit> {
        return authDataRepository.isAuthenticated()
    }


}