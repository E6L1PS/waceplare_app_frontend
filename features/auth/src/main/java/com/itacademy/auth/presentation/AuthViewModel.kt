package com.itacademy.auth.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.auth.domain.usecase.AuthUseCase
import com.itacademy.common.Resource
import com.itacademy.common.model.AuthenticationRequest
import com.itacademy.common.model.RegisterRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
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
                when (val result = authUseCase.isAuthenticated()) {
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
                when (val result = authUseCase.signIn(authenticationRequest)) {
                    is Resource.Success -> Resource.Success(Unit)
                    is Resource.Error -> Resource.Error(result.message)
                    is Resource.Loading -> Resource.Loading(Unit)
                }
            )
        }
    }

    fun signUp(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            _isAuthenticated.emit(
                when (val result = authUseCase.signUp(registerRequest)) {
                    is Resource.Success -> Resource.Success(Unit)
                    is Resource.Error -> Resource.Error(result.message)
                    is Resource.Loading -> Resource.Loading(Unit)
                }
            )
        }
    }




}