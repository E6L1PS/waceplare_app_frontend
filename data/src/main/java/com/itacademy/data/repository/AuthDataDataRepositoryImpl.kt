package com.itacademy.data.repository

import android.content.SharedPreferences
import com.itacademy.common.model.AuthResult
import com.itacademy.data.AuthDataRepository
import com.itacademy.data.api.AuthApi
import com.itacademy.data.util.Constants
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.model.RegisterRequest
import retrofit2.HttpException
import javax.inject.Inject

class AuthDataDataRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthDataRepository {

    override suspend fun signUp(registerRequest: RegisterRequest): AuthResult<Unit> {
        return try {
            val response = api.signUp(registerRequest)
            sharedPreferences.edit()
                .putString("jwt_token", response.token)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(authenticationRequest: AuthenticationRequest): AuthResult<Unit> {
        return try {
            val response = api.signIn(authenticationRequest)

            sharedPreferences.edit()
                .putString(Constants.JWT_KEY, response.token)
                .apply()

            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun isAuthenticated(): AuthResult<Unit> {
        return try {
            if (api.isAuthenticated()) {
                AuthResult.Authorized()
            } else {
                AuthResult.Unauthorized()
            }
        } catch (e: HttpException) {
            if (e.code() == 403 || e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}