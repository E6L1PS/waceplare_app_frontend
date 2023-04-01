package com.itacademy.waceplare.util

import android.content.SharedPreferences
import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString("jwt_token", null)
        val request = chain.request()
        Log.i("okHttp.JWT", token.toString())

        val authenticatedRequest = if (token != null) {
            Log.i("okHttp.JWT", token)
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }
        return chain.proceed(authenticatedRequest)
    }
}