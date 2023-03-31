package com.itacademy.waceplare.data.api

import com.itacademy.waceplare.data.model.auth.AuthRequest
import com.itacademy.waceplare.data.model.auth.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    suspend fun signUp(@Body request: AuthRequest)

    @POST("authenticate")
    suspend fun signIn(@Body request: AuthRequest): TokenResponse

}