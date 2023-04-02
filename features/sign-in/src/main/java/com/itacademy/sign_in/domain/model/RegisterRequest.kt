package com.itacademy.sign_in.domain.model

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String
    )
