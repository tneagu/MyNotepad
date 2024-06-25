package com.tneagu.addnote.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tneagu.addnote.domain.usecase.AddNoteUseCase
import com.tneagu.addnote.presentation.model.AddNoteState
import com.tneagu.appnavigation.AppNavigator
import com.tneagu.domain.entities.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _addNoteState = MutableStateFlow<AddNoteState>(AddNoteState.InitialState)
    val addNoteState = _addNoteState.asStateFlow()

    fun saveNote(title: String, content: String){
        viewModelScope.launch {
            val note = Note(
                title = title,
                body = content,
            )

            addNoteUseCase.invoke(note).also {
                appNavigator.back()
            }
        }
    }
}