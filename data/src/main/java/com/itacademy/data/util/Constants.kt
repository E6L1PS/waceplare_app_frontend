package com.itacademy.data.util

object Constants {
    const val BASE_URL = "http://192.168.0.106:8080/api/v1/"
    const val JWT_KEY = "jwt"
    val excludedUrls = listOf(
        "/api/v1/auth/authenticate", "/api/v1/auth/register", "/api/v1/ads", "/api/v1/ads/{id}"
    )
}