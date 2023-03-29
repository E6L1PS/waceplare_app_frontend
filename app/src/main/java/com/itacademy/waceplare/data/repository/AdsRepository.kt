package com.itacademy.waceplare.data.repository

import com.itacademy.waceplare.data.api.SearchApi
import com.itacademy.waceplare.data.model.Ad
import com.itacademy.waceplare.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AdsRepository @Inject constructor(
    private val api: SearchApi
) {

    suspend fun getAds(title: String?): Flow<Resource<List<Ad>?>> = flow {
        emit(Resource.loading(null))

        val response = if (title == null) {
            api.getAllAds()
        } else {
            api.getAllAds(title)
        }

        if (response.isSuccessful) {
            emit(Resource.success(response.body()))
        } else {
            emit(Resource.error(response.message(), null))
        }

    }.catch { exception ->
        emit(Resource.error(exception.message ?: "Error occurred", null))
    }.flowOn(Dispatchers.IO)


}