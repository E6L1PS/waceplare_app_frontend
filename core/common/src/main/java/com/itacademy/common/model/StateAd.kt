package com.itacademy.common.model

enum class StateAd(
    private val displayName: String? = null
) {
    NEW("Новое"),
    USED("Б/у");


    fun getDisplayName(): String? {
        return displayName
    }
}