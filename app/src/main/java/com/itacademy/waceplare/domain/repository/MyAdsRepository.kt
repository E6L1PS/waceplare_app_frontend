package com.itacademy.waceplare.domain.repository

import com.itacademy.data.model.Ad
import com.itacademy.waceplare.domain.model.AdDTO
import kotlinx.coroutines.flow.Flow

interface MyAdsRepository {

    suspend fun getAds(): Flow<Resource<List<Ad>?>>

    suspend fun postAd(ad: AdDTO)

}
