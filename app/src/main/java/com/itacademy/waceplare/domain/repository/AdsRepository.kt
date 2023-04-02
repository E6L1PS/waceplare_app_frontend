package com.itacademy.waceplare.domain.repository

import com.itacademy.data.model.Ad
import kotlinx.coroutines.flow.Flow

interface AdsRepository {

    suspend fun getAds(title: String?): Flow<Resource<List<Ad>?>>
}