package com.itacademy.data

import com.itacademy.data.model.auth.AuthResult
import com.itacademy.data.model.auth.AuthenticationRequest
import com.itacademy.data.model.auth.RegisterRequest

interface AuthDataRepository {
    suspend fun signIn(authenticationRequest: AuthenticationRequest): AuthResult<Unit>
    suspend fun signUp(registerRequest: RegisterRequest): AuthResult<Unit>
    suspend fun isAuthenticated(): AuthResult<Unit>
}
