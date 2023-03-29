package com.itacademy.waceplare.data.model

data class User(
    val favoriteAds: List<Any>,
    val id: Int,
    val name: String,
    val number: String,
    val rating: Int
)