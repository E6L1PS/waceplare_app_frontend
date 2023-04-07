package com.itacademy.personal_ads.domain.model

enum class StateAd(
    private val displayName: String? = null
) {
    NEW("Новое"),
    USED("Б/у");


    fun getDisplayName(): String? {
        return displayName
    }
}