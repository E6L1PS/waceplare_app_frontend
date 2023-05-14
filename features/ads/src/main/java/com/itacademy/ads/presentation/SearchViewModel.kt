package com.itacademy.ads.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.common.model.AdWithIsFavorite
import com.itacademy.ads.domain.usecase.FavoritesUseCase
import com.itacademy.ads.domain.usecase.SearchAdsByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchAdsByTitleUseCase: SearchAdsByTitleUseCase,
    private val favoritesUseCase: FavoritesUseCase,

    ) : ViewModel() {

    private val _ads = MutableStateFlow<Resource<List<Ad>?>>(Resource.loading(null))
    val ads: StateFlow<Resource<List<Ad>?>> = _ads.asStateFlow()

    // private val _adsWithFavorite = MutableStateFlow<Resource<List<AdModel>?>>(Resource.loading(null))
   /* val adsWithFavorite: StateFlow<Resource<List<AdWithIsFavorite>?>> =
        combine(_ads, _favorites) { adsResource, favoritesResource ->

            if (adsResource is Resource.Success) {

                val ads = if (favoritesResource is Resource.Success) {
                    val favRes = favoritesResource.data.orEmpty()
                    // преобразовываем List<Ad> в List<AdModel>
                    adsResource.data.orEmpty().map { ad ->
                        AdWithIsFavorite(
                            id = ad.id,
                            title = ad.title,
                            description = ad.description,
                            price = ad.price,
                            views = ad.views,
                            status = ad.status,
                            dateOfCreated = ad.dateOfCreated,
                            comments = ad.comments,
                            type = ad.type,
                            state = ad.state,
                            isFavorite = favoritesResource.data.orEmpty().contains(ad.id)
                        )
                    }

                } else {
                    adsResource.data.orEmpty().map { ad ->
                        AdWithIsFavorite(
                            id = ad.id,
                            title = ad.title,
                            description = ad.description,
                            price = ad.price,
                            views = ad.views,
                            status = ad.status,
                            dateOfCreated = ad.dateOfCreated,
                            comments = ad.comments,
                            type = ad.type,
                            state = ad.state,
                            isFavorite = false
                        )
                    }

                }
                Resource.success(ads)
            } else {
                Resource.error("Error fetching data", null)
            }
        }.stateIn(viewModelScope, SharingStarted.Lazily, Resource.loading(null))*/

    init {
        getAds(null)
    }

    fun getAds(title: String?) {
        viewModelScope.launch {
            _ads.emitAll(searchAdsByTitleUseCase.searchByTitle(title))
        }
    }





}