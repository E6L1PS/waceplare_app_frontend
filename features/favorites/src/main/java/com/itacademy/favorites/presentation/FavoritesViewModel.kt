package com.itacademy.favorites.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.favorites.domain.usecase.FavoritesUseCase
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

    init {
        getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch {
            _favorites.emitAll(favoritesUseCase.getFavorites())
        }
    }

    fun addFavorite(adId: Long) {
        TODO("Not yet implemented")
    }

    fun deleteFavorite(adId: Long) {
        TODO("Not yet implemented")
    }

    fun deleteFavorites() {
        viewModelScope.launch {
            favoritesUseCase.deleteFavorites()
            getFavorites()
        }
    }

    fun deleteInactiveFavorites() {
        viewModelScope.launch {
           // favoritesUseCase.deleteInactiveFavorites()
            getFavorites()
        }
    }

}