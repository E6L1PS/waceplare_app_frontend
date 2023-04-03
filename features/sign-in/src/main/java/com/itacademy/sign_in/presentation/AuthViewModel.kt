package com.itacademy.sign_in.presentation

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
    private val signInUseCase: SignInUseCase,
    private val router: SignInRouter,
): ViewModel() {


    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    init {
        isAuthorized()
    }


    fun isAuthorized() {

        viewModelScope.launch {
            _isAuthenticated.emit(
                when (signInUseCase.isAuthenticated()) {
                    is AuthResult.Authorized -> true
                    else -> false
                }
            )
        }
    }

    fun signIn(authenticationRequest: AuthenticationRequest) {
        viewModelScope.launch {
            _isAuthenticated.emit(
                when (signInUseCase.signIn(authenticationRequest)) {
                    is AuthResult.Authorized -> {
                        true
                    }
                    else -> false
                }
            )
        }
    }




}