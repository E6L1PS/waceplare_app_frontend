package com.itacademy.sign_in.domain.usecase

import com.itacademy.sign_in.domain.model.AuthResult
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
){
    suspend fun signIn(authenticationRequest: AuthenticationRequest): AuthResult<Unit> {
        /* TODO if need
        if (authenticationRequest.email.isBlank()) throw EmptyEmailException()
        if (authenticationRequest.password.isBlank() throw EmptyPasswordException()
*/
        return authRepository.signIn(authenticationRequest)
    }

    suspend fun isAuthenticated(): AuthResult<Unit> {
        return authRepository.isAuthenticated()
    }

}