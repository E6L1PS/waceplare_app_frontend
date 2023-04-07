package com.itacademy.common.model

enum class TypeAd(
    private val displayName: String? = null
) {
    CAR("Автомобили"),
    REAL_ESTATE("Недвижимость"),
    JOB("Работа"),
    SERVICE("Услуги"),
    STUFF("Вещи"),
    BUSINESS("Бизнес");


    fun getDisplayName(): String? {
        return displayName
    }
}
