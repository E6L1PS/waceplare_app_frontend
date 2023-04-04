package com.itacademy.search

import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import kotlinx.coroutines.flow.Flow

interface AdsRepository {
    suspend fun getAds(title: String?): Flow<Resource<List<Ad>?>>
}
