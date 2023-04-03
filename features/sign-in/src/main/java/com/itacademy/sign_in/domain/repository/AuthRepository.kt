package com.itacademy.sign_in.domain.repository

import com.itacademy.common.model.AuthResult
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.model.RegisterRequest

interface AuthRepository {
    suspend fun signIn(authenticationRequest: AuthenticationRequest): AuthResult<Unit>
    suspend fun signUp(registerRequest: RegisterRequest): AuthResult<Unit>
    suspend fun isAuthenticated(): AuthResult<Unit>
}
