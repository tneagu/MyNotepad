package com.tneagu.auth.login.presentation

import androidx.compose.runtime.Composable
import com.tneagu.auth.login.presentation.ui.LoginForm

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> (Unit),
) {

    LoginForm(
        onLoginClick = onLoginClick,
    )
}