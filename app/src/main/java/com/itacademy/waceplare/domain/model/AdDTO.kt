package com.itacademy.waceplare.domain.model

import com.itacademy.data.model.Category

data class AdDTO(
    val price: Int,
    val title: String,
    val description: String,
    val category: Category
)