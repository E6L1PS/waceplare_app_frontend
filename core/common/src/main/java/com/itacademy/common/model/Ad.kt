package com.itacademy.common.model

data class Ad(
    val id: Long,
    val price: Int,
    val views: Int,
    val favorites: Int,
    val title: String,
    val description: String,
    val status: Boolean,
    val dateOfCreated: String,
    val userInfo: UserInfo,
    val type: TypeAd,
    val state: StateAd?,
    val comments: List<Comment>,
    val images: List<AdImage>
)