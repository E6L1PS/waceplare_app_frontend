package com.itacademy.data

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdDTO
import kotlinx.coroutines.flow.Flow

interface PersonalAdsDataRepository {
    suspend fun getAds(): Flow<Resource<List<Ad>?>>
    suspend fun postAd(ad: AdDTO)
}
