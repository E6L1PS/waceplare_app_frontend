package com.itacademy.waceplare.domain.repository

import com.itacademy.waceplare.data.model.Ad
import com.itacademy.waceplare.util.Resource
import kotlinx.coroutines.flow.Flow

interface AdsRepository {

    suspend fun getAds(title: String?): Flow<Resource<List<Ad>?>>
}