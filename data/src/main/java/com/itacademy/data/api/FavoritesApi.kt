package com.itacademy.data.api

import com.itacademy.common.model.Ad
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FavoritesApi {

    @GET("favorites")
    suspend fun getFavoriteAds(): Response<List<Ad>>

    @GET("favorites/id")
    suspend fun getFavoriteAdsId(): Response<List<Long>>

    @POST("favorites/{adId}")
    suspend fun addFavoriteAd(@Path(value = "adId") adId: Long)

    @DELETE("favorites/{adId}")
    suspend fun deleteFavoriteAd(@Path(value = "adId") adId: Long)

}