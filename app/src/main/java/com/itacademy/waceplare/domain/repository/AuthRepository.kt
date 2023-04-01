package com.itacademy.waceplare.domain.repository

import com.itacademy.waceplare.data.model.auth.AuthResult
import com.itacademy.waceplare.data.model.auth.AuthenticationRequest
import com.itacademy.waceplare.data.model.auth.RegisterRequest

interface AuthRepository {
    suspend fun signUp(registerRequest: RegisterRequest): AuthResult<Unit>
    suspend fun signIn(authenticationRequest: AuthenticationRequest): AuthResult<Unit>
    suspend fun isAuthenticated(): AuthResult<Unit>
}