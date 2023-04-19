package com.itacademy.personal_ads.domain.repository

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.personal_ads.domain.model.AdDTO
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PersonalAdsRepository {
    suspend fun getAds(): Flow<Resource<List<Ad>?>>

    suspend fun postAd(ad: AdDTO)

    suspend fun uploadImages(adId: Long, images: List<File>)
    
}
