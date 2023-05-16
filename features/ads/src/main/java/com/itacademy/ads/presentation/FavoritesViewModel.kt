package com.itacademy.ads.presentation

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.ads.domain.usecase.FavoritesUseCase
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: FavoritesUseCase
) : ViewModel() {
    private val _favorites = MutableStateFlow<Resource<List<Ad>?>>(Resource.loading(null))
    val favorites: StateFlow<Resource<List<Ad>?>> = _favorites.asStateFlow()


    private val _favoriteIds = MutableStateFlow<Resource<List<Long>?>>(Resource.loading(null))
    val favoriteIds: StateFlow<Resource<List<Long>?>> = _favoriteIds.asStateFlow()

    init {
        init()
    }

    fun init() {
        getFavorites()
        getFavoriteIds()
    }
    fun getFavorites() {
        viewModelScope.launch {
            _favorites.emitAll(favoritesUseCase.getFavorites())
        }
    }


    fun getFavoriteIds() {
        viewModelScope.launch {
            _favoriteIds.emitAll(favoritesUseCase.getFavoritesId())
        }
    }

    fun addFavorite(adId: Long) {
        viewModelScope.launch {
            favoritesUseCase.addFavorite(adId)
            init()
        }
    }

    fun deleteFavorite(adId: Long) {
        viewModelScope.launch {
            favoritesUseCase.deleteFavorite(adId)
            init()
        }
    }

    fun deleteFavorites() {
        viewModelScope.launch {
            favoritesUseCase.deleteFavorites()
            init()
        }
    }

    fun deleteInactiveFavorites() {
        viewModelScope.launch {
            favoritesUseCase.deleteInactiveFavorites()
            init()
        }
    }

}

