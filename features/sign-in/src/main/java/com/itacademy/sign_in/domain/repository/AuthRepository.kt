package com.itacademy.sign_in.domain.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.AuthResult
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.model.RegisterRequest

interface AuthRepository {
    suspend fun signIn(authenticationRequest: AuthenticationRequest): Resource<Unit>
    suspend fun signUp(registerRequest: RegisterRequest): Resource<Unit>
    suspend fun isAuthenticated(): Resource<Unit>
}
