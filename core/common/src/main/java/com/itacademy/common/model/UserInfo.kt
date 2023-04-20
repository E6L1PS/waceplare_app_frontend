package com.itacademy.common.model

data class UserInfo(
    val id: Long,
    val firstname: String,
    val lastname: String,
    val email: String,
    val number: String,
    val rating: Int,
    val dateOfCreated: String,
)