package com.itacademy.ads.domain.model

data class AdDTO(
    val price: Int,
    val title: String,
    val description: String,
    val category: Category
)