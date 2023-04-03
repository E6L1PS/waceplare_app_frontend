package com.itacademy.personal_ads.domain.model

data class Ad(
    val id: Long,
    val price: Int,
    val views: Int,
    val title: String,
    val description: String,
    val status: Boolean,
    val dateOfCreated: String,
    val user: User,
    val category: Category,
    val comments: List<Any>
)