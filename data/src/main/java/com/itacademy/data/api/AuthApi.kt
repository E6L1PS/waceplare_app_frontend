package com.itacademy.data.api

import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.model.RegisterRequest
import com.itacademy.sign_in.domain.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/register")
    suspend fun signUp(@Body request: RegisterRequest): Response<TokenResponse>

    @POST("auth/authenticate")
    suspend fun signIn(@Body request: AuthenticationRequest): Response<TokenResponse>

    @GET("auth/check")
    suspend fun isAuthenticated(): Response<Boolean>

}