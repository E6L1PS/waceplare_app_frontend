package com.itacademy.sign_in.domain.model

data class AuthenticationRequest(
    val email: String,
    val password: String
)