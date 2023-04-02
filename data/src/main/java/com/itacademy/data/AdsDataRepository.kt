package com.itacademy.data

import com.itacademy.common.Resource
import com.itacademy.data.model.Ad
import kotlinx.coroutines.flow.Flow

interface AdsDataRepository {
    suspend fun getAds(title: String?): Flow<Resource<List<Ad>?>>
}
