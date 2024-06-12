package com.tneagu.auth.login.presentation.model

sealed class LoginState {

    data object Uninitialized : LoginState()
    data object LoggedIn : LoginState()
    data object NotAuthenticated : LoginState()
    data object Loading : LoginState()
    data object Error : LoginState()
}