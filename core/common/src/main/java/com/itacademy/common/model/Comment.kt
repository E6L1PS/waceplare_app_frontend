package com.itacademy.common.model

import java.time.LocalDateTime

data class Comment(
    val id: Long,
    val text: String,
    val date: LocalDateTime,
)