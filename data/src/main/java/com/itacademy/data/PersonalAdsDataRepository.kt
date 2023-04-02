package com.itacademy.data

import com.itacademy.common.Resource
import com.itacademy.data.model.Ad
import com.itacademy.data.model.AdDTO
import kotlinx.coroutines.flow.Flow

interface PersonalAdsDataRepository {
    suspend fun getAds(): Flow<Resource<List<Ad>?>>
    suspend fun postAd(ad: AdDTO)
}
