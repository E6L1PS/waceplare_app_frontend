package com.itacademy.waceplare.util

import android.content.SharedPreferences
import android.util.Log
import com.itacademy.data.util.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(Constants.JWT_KEY, null)
        val request = chain.request()

        //TODO change logic
        Log.i("okHttp.JWT", token.toString())
        Log.i("okHttp.JWT", request.url.encodedPath)
        val authenticatedRequest = if (isAuthRequired(request.url.encodedPath) && token != null) {
            Log.i("okHttp.JWT", request.url.toString())
            request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        } else {
            request
        }
        return chain.proceed(authenticatedRequest)
    }

    private fun isAuthRequired(url: String): Boolean {
        Log.d("okHttp.JWT", Constants.excludedUrls.contains(url).toString())
        // Проверяем, нужно ли добавлять токен к текущему URL
        return !Constants.excludedUrls.contains(url)
    }

}