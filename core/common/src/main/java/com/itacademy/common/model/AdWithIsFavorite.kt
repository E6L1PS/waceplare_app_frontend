package com.itacademy.common.model

data class AdWithIsFavorite(
    val id: Long,
    val price: Int,
    val views: Int,
    val title: String,
    val description: String,
    val status: Boolean,
    val dateOfCreated: String,
    val type: TypeAd,
    val state: StateAd?,
    val comments: List<Comment>,
    val isFavorite: Boolean
)