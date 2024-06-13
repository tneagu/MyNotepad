package com.tneagu.auth.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tneagu.appnavigation.AppNavigator
import com.tneagu.auth.login.domain.usecases.CurrentUserUseCase
import com.tneagu.auth.login.domain.usecases.LoginUseCase
import com.tneagu.auth.login.presentation.model.LoginState
import com.tneagu.noteslist.NotesListNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val loginUseCase: LoginUseCase,
    private val currentUserUseCase: CurrentUserUseCase,
) : ViewModel() {

    val _state = MutableStateFlow<LoginState>(LoginState.Uninitialized)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val currentUser = currentUserUseCase.invoke()
            if (currentUser == null) {
                _state.value = LoginState.NotAuthenticated
            } else {
                appNavigator.navigate(NotesListNavigation.openNoteList())
            }
        }
    }

    fun login(email: String, password: String) {
        _state.value = LoginState.Loading
        viewModelScope.launch {
            val isSuccess = loginUseCase(email, password)
            if (isSuccess) {
                appNavigator.navigate(NotesListNavigation.openNoteList())
            } else {
                _state.value = LoginState.Error
            }
        }
    }
}