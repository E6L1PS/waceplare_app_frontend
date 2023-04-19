package com.itacademy.common.model

data class AdModel(
    val id: Long,
    val price: Int,
    val views: Int,
    val title: String,
    val description: String,
    val status: Boolean,
    val dateOfCreated: String,
    val comments: List<Any>,
    val isFavorite: Boolean
)
