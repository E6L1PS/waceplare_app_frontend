package com.itacademy.data.api

import com.itacademy.common.model.Ad
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {

    @GET("ads")
    suspend fun getAllAds(): Response<List<Ad>>

    @GET("ads/{id}")
    suspend fun getAd(@Path(value = "id") id: Long): Response<Ad>

    @GET("ads/title")
    suspend fun getAllAds(@Query("title") title: String): Response<List<Ad>>

}