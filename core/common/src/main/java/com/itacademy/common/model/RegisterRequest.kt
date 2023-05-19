package com.itacademy.common.model

data class RegisterRequest(
    val firstname: String,
    val lastname: String,
    val number: String,
    val email: String,
    val password: String
    )
