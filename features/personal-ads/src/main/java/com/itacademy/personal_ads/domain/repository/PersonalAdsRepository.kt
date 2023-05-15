package com.itacademy.personal_ads.domain.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdDTO

import kotlinx.coroutines.flow.Flow

interface PersonalAdsRepository {
    suspend fun getAds(): Flow<Resource<List<Ad>?>>

    suspend fun postAd(ad: AdDTO): Resource<Long?>

    suspend fun uploadImages(adId: Long, images: List<ByteArray?>)
    suspend fun postAdWithImages(adDTO: AdDTO, images: List<ByteArray?>)

}
