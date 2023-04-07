package com.itacademy.common.model

data class AdDTO(
    val price: Int,
    val title: String,
    val description: String,
    val type: TypeAd,
    val state: StateAd?
)