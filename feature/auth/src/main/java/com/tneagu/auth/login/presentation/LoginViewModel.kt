package com.tneagu.auth.login.presentation

import androidx.lifecycle.ViewModel
import com.tneagu.appnavigation.AppNavigator
import com.tneagu.noteslist.NotesListNavigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun login(username: String, password: String) {
        appNavigator.navigate(NotesListNavigation.openNoteList())
    }
}