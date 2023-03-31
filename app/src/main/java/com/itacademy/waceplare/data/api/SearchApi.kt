package com.itacademy.waceplare.data.api

import com.itacademy.waceplare.data.model.Ad
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("ads")
    suspend fun getAllAds(): Response<List<Ad>>


    @GET("ads/title")
    suspend fun getAllAds(@Query("title") title: String): Response<List<Ad>>

}