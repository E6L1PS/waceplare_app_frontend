package com.itacademy.data.model.auth

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String
    )
