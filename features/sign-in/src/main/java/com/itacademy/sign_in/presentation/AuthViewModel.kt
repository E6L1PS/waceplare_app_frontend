package com.itacademy.sign_in.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.Resource
import com.itacademy.sign_in.domain.model.AuthenticationRequest
import com.itacademy.sign_in.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
): ViewModel() {

    private val _isAuthenticated =  MutableStateFlow<Resource<Unit>>(Resource.loading(null))
    val isAuthenticated: StateFlow<Resource<Unit>> = _isAuthenticated.asStateFlow()

    init {
        isAuthorized()
    }

    fun isAuthorized() {

        Log.d("AuthTEST", "called")

        viewModelScope.launch {
            _isAuthenticated.emit(
                when (val result = signInUseCase.isAuthenticated()) {
                    is Resource.Success -> Resource.Success(Unit)
                    is Resource.Error -> Resource.Error(result.message)
                    is Resource.Loading -> Resource.Loading(Unit)
                }
            )
        }
    }

    fun signIn(authenticationRequest: AuthenticationRequest) {
        viewModelScope.launch {
            _isAuthenticated.emit(
                when (val result = signInUseCase.signIn(authenticationRequest)) {
                    is Resource.Success -> Resource.Success(Unit)
                    is Resource.Error -> Resource.Error(result.message)
                    is Resource.Loading -> Resource.Loading(Unit)
                }
            )
        }
    }




}