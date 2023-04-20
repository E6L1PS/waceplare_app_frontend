package com.itacademy.data

import com.itacademy.common.Resource
import com.itacademy.common.model.AuthenticationRequest
import com.itacademy.common.model.RegisterRequest


interface AuthDataRepository {
    suspend fun signIn(authenticationRequest: AuthenticationRequest): Resource<Unit>
    suspend fun signUp(registerRequest: RegisterRequest): Resource<Unit>
    suspend fun isAuthenticated(): Resource<Unit>
    suspend fun logOut()

}
