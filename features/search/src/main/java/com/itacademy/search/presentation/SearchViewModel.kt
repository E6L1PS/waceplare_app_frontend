package com.itacademy.search.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.common.model.Ad
import com.itacademy.search.domain.usecase.SearchAdsByTitleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchAdsByTitleUseCase: SearchAdsByTitleUseCase
) : ViewModel() {

    private val _ads = MutableStateFlow<Resource<List<Ad>?>>(Resource.loading(null))
    val ads: StateFlow<Resource<List<Ad>?>> = _ads.asStateFlow()

    init {
        getAds(null)
    }

    fun getAds(title: String?) {
        viewModelScope.launch {
            _ads.emitAll(searchAdsByTitleUseCase.searchByTitle(title))
        }
    }


}
