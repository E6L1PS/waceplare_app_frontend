package com.itacademy.sign_in.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itacademy.common.model.AuthResult
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


    private val _isAuthenticated =  MutableStateFlow<AuthResult<Boolean>>(AuthResult.UnknownError())
    val isAuthenticated: StateFlow<AuthResult<Boolean>> = _isAuthenticated.asStateFlow()

    init {
        isAuthorized()
    }


    fun isAuthorized() {

        Log.d("AuthTEST", "called")

        viewModelScope.launch {
            _isAuthenticated.emit(
                when (signInUseCase.isAuthenticated()) {
                    is AuthResult.Authorized -> AuthResult.Authorized(true)
                    is AuthResult.Unauthorized -> AuthResult.Unauthorized()
                    is AuthResult.UnknownError -> AuthResult.UnknownError()
                }
            )
        }
    }

    fun signIn(authenticationRequest: AuthenticationRequest) {
        viewModelScope.launch {
            _isAuthenticated.emit(
                when (signInUseCase.signIn(authenticationRequest)) {
                    is AuthResult.Authorized -> AuthResult.Authorized(true)
                    is AuthResult.Unauthorized -> AuthResult.Unauthorized()
                    is AuthResult.UnknownError -> AuthResult.UnknownError()
                }
            )
        }
    }




}