package com.itacademy.auth.domain.usecase

import com.itacademy.auth.domain.repository.AuthRepository
import com.itacademy.common.Resource
import com.itacademy.common.model.AuthenticationRequest
import com.itacademy.common.model.RegisterRequest
import javax.inject.Inject

class AuthUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend fun signIn(authenticationRequest: AuthenticationRequest): Resource<Unit> {
        /* TODO if need
        if (authenticationRequest.email.isBlank()) throw EmptyEmailException()
        if (authenticationRequest.password.isBlank() throw EmptyPasswordException()
*/
        return authRepository.signIn(authenticationRequest)
    }

    suspend fun signUp(registerRequest: RegisterRequest): Resource<Unit> {
        /* TODO if need
        if (authenticationRequest.email.isBlank()) throw EmptyEmailException()
        if (authenticationRequest.password.isBlank() throw EmptyPasswordException()
*/
        return authRepository.signUp(registerRequest)
    }


    suspend fun isAuthenticated(): Resource<Unit> {
        return authRepository.isAuthenticated()
    }

}