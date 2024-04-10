package com.tneagu.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tneagu.appnavigation.AppNavigator
import com.tneagu.auth.R
import com.tneagu.auth.login.domain.LoginUseCase
import com.tneagu.auth.login.domain.LoginUseCaseImpl
import com.tneagu.auth.login.presentation.model.LoginState
import com.tneagu.noteslist.NotesListNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val loginUseCaseImpl: LoginUseCase
) : ViewModel() {

    val _state = MutableStateFlow<LoginState>(LoginState.NotAuthenticated)
    val state = _state.asStateFlow()

    fun login(email: String, password: String) {
        _state.value = LoginState.Loading
        viewModelScope.launch {
            val isSuccess = loginUseCaseImpl.login(email, password)
            if (isSuccess) {
                appNavigator.navigate(NotesListNavigation.openNoteList())
            } else {
                _state.value = LoginState.Error
            }
        }
    }
}