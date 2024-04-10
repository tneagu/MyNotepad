package com.tneagu.auth.login.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tneagu.auth.R
import com.tneagu.auth.login.presentation.model.LoginState
import com.tneagu.auth.login.presentation.ui.LoginForm
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    state: LoginState,
    onLoginClick: (String, String) -> (Unit),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val errorMessage = stringResource(id = R.string.login_error)

    LaunchedEffect(state) {
        if (state is LoginState.Error) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = errorMessage,
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            LoginForm(
                isLoading = state is LoginState.Loading,
                onLoginClick = onLoginClick,
            )

        }

    }

}